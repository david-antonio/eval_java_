package cl.prueba.restfull.login.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


public class LoginUserResp extends Estado{
    
  
	@JsonInclude(Include.NON_NULL)
	private String mensaje;

	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastlogin;

	@JsonInclude(Include.NON_NULL)
	private Boolean isactive;

	@JsonInclude(Include.NON_NULL)
	private String token;

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Date getLastlogin() {
        return lastlogin;
    }

    public void setLastlogin(Date lastlogin) {
        this.lastlogin = lastlogin;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUserResp() {
    }

    public LoginUserResp(User user) {
        super();
        this.lastlogin = user.getLastlogin();
        this.isactive = user.getIsactive();
        this.token = user.getUuid();
    }

}