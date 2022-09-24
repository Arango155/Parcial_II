import Clases.Libro;
import Clases.LibroController;
import Clases.ConexionBaseDeDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(urlPatterns = {"/NewServlet"})
public class NewServlet extends HttpServlet {
    Libro libro;
    LibroController registroLibro;
     Libro[] LibrosRegistrados;
     StringBuffer objetoRespuesta = new StringBuffer();
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter respuesta = response.getWriter()) {            
           
           registroLibro=new LibroController();
           String control = request.getParameter("control");
           
           if(control.toUpperCase().equals("GUARDAR")){
               libro=new Libro(
                Integer.parseInt(request.getParameter("codigo")),
                request.getParameter("nombre_libro"),
                request.getParameter("tipo_de_pasta"),
                request.getParameter("editorial"),
                Integer.parseInt(request.getParameter("anio_de_publicacion")));                
                registroLibro.guardarLibro2(libro);//almacenarlo en BD                 
           }else if(control.toUpperCase().equals("ELIMINAR")){
               int codigoEliminar= Integer.parseInt(request.getParameter("codigo_libro"));
               registroLibro.eliminarLibro(codigoEliminar);
           }
                        
            
            registroLibro.guardarAlumno(libro);//almacenarlo en el array
            LibrosRegistrados= registroLibro.getLibro();// consultar alumnos en el array                       
                    
           registroLibro.getLibro2(objetoRespuesta);//consultar alumnos en la BD
           respuesta.write(objetoRespuesta.toString());             
            
           
            for (int i = 0; i < LibrosRegistrados.length; i++){
                   //if(!alumnosRegistrados[i].getCodigo().isEmpty()){
                    if(LibrosRegistrados[i].getCodigo()>0){
                       respuesta.println("<tr><td>" + LibrosRegistrados[i].getCodigo()+ "</td>");
                       respuesta.println("<td>" + LibrosRegistrados[i].getNombre_libro() + "</td>");
                       respuesta.println("<td>" + LibrosRegistrados[i].getTipo_de_pasta()+ "</td>");
                       respuesta.println("<td>" + LibrosRegistrados[i].getEditorial()+ "</td>");
                       respuesta.println("<td>" + LibrosRegistrados[i].getAnio_publicacion()+ "</td>");
                       respuesta.println("<td>"
                               + "<button type=\"button\" class=\"btn btn-warning\"></i>Editar</button> "
                               + "<button type=\"button\" class=\"btn btn-danger\">Eliminar</button>"
                               + "</td></tr>");
                    }
                }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}