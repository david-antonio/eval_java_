package cl.prueba.restfull.login.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.prueba.restfull.login.model.CreateUserResp;
import cl.prueba.restfull.login.model.Estado;
import cl.prueba.restfull.login.model.LoginUserResp;
import cl.prueba.restfull.login.model.User;
import cl.prueba.restfull.login.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/apirest")
public class UserController {

	@Autowired
	UserRepository userRepository;

	static private String REG_EXP_MAIL = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";

	/**
	 * Metodo get para obtener usuario
	 * @param email
	 * @return 
	 */
	@GetMapping("/users/{email}")
	public ResponseEntity<CreateUserResp> getUserByEmail(@PathVariable("email") String email) {
		List<User> usuarios = userRepository.findByEmail(email);
		CreateUserResp createUserResp = new CreateUserResp();
		if (!usuarios.isEmpty()) {
			BeanUtils.copyProperties(usuarios.get(0), createUserResp);
			return new ResponseEntity<>(createUserResp, HttpStatus.OK);
		} else {
			createUserResp.setMensaje("Usuario no registrado");
			return new ResponseEntity<>(createUserResp, HttpStatus.NOT_FOUND);
		}
	}

	/**
	 * Metodo post para creacion de usuario
	 * @param req
	 * @return
	 */
	@PostMapping("/users")
	public ResponseEntity<CreateUserResp> createUser(@RequestBody User req) {
		try {
			CreateUserResp createUserResp = new CreateUserResp();
			String mensajeValidacion = validacionIngreso(req);
			if (mensajeValidacion != null) {
				createUserResp.setMensaje(mensajeValidacion);
				return new ResponseEntity<>(createUserResp, HttpStatus.CONFLICT);
			}
        	Date sqlDate = new Date(System.currentTimeMillis());
			req = userRepository
					.save(new User(req.getName(),req.getEmail(), req.getPassword(), req.getPhone(),sqlDate,sqlDate,sqlDate));
			BeanUtils.copyProperties(req, createUserResp);
			return new ResponseEntity<>(createUserResp, HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Metodo post que permite el login de usuario actualizando fecha de ultimo login y retornando un nuevo session uuid
	 * @param user
	 * @return
	 */
	@PostMapping("/users/login")
	public ResponseEntity<LoginUserResp> loginUser(@RequestBody User user) {
		try {
			LoginUserResp loginUserResp = new LoginUserResp();
			List<User> usuarioResp = userRepository.findByEmail(user.getEmail());
			String mensajeValidacion = validacionLogin(usuarioResp, user);
			if (mensajeValidacion != null) {
				loginUserResp.setMensaje(mensajeValidacion);
				return new ResponseEntity<>(loginUserResp, HttpStatus.UNAUTHORIZED);
			}
			final UUID uuidSession = UUID.randomUUID();
			User usuarioLogin = usuarioResp.get(0);
			
        	Date sqlDate = new Date(System.currentTimeMillis());
			usuarioLogin.setUuid(uuidSession.toString());
			usuarioLogin.setLastlogin(sqlDate);
			usuarioLogin = userRepository.saveAndFlush(usuarioLogin);
			loginUserResp = new LoginUserResp(usuarioLogin);
			return new ResponseEntity<>(loginUserResp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	/**
	 * Eliminacion de usuarios
	 * Exponer solo al administrador
	 * @param email
	 * @return
	 */
	@DeleteMapping("/users/{email}")
	public ResponseEntity<Estado> deleteUser(@PathVariable String email) {
		try {
			Estado estado = new Estado();
			List<User> usuarios = userRepository.findByEmail(email);
			if (!usuarios.isEmpty()) {
				userRepository.delete(usuarios.get(0));
				estado.setMensaje("Eliminado correctamente");
				return new ResponseEntity<>(estado, HttpStatus.OK);
			} else {
				estado.setMensaje("Usuario no encontrado");
				return new ResponseEntity<>(estado, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * @return retorna el mensaje en caso de error y null en caso OK
	 */
	private String validacionIngreso(User user) { 
		List<User> usuarioResp = userRepository.findByEmail(user.getEmail());
		if (!usuarioResp.isEmpty()) {
			return "Correo ya registrado";
		} else if(!Pattern.compile(REG_EXP_MAIL).matcher(user.getEmail()).matches()) {
			return "Correo invalido";
		}
		return null;
	}

	/**
	 * @return retorna el mensaje en caso de error y null en caso OK
	 */
	private String validacionLogin(List<User> usuarioResp, User user) { 
		if (usuarioResp.isEmpty() || !usuarioResp.get(0).getPassword().equals(user.getPassword())) {
			return "Usuario o clave invalida";
		}
		return null;
	}

}
