package ec.edu.ups.Utils;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.Facelet;
import javax.inject.Named;
import org.primefaces.model.UploadedFile;

@Named(value = "fileUploadBean")
@ManagedBean
@ViewScoped
public class FileUploadServlet implements Serializable{
	
	private static final long serialVersionUID = 4352236420460919694L;
	
	private UploadedFile file;  
    
    public UploadedFile getFile() {  
        return file;  
    }  
  
    public void setFile(UploadedFile file) {  
        this.file = file;  
    }  
  
    public void upload() {  
        FacesMessage msg = new FacesMessage("Ok", "Fichero " + file.getFileName() + " subido correctamente.");
    	FacesContext.getCurrentInstance().addMessage(null, msg);
    }  
    

}
