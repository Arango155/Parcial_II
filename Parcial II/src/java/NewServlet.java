
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
     Libro[] librosRegistrados;

    
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
            libro=new Libro(
                Integer.parseInt(request.getParameter("codigo")),
                request.getParameter("nombre_libro"),
                request.getParameter("tipo_de_pasta"),
                request.getParameter("editorial"),
                request.getParameter("anio_publicacion")
                
            );               
                        
            if(registroLibro==null){
                 registroLibro=new LibroController();
            }
           
            registroLibro.guardarLibro(libro);//almacenarlo en el array
            
           if(registroLibro.getLibro2(libro)){//almacenarlo en BD
               respuesta.println(1);
           }else{
               respuesta.println(0);
           }
            librosRegistrados= registroLibro.getLibro();           
           
            for (int i = 0; i < librosRegistrados.length; i++){
                    if(librosRegistrados[i].getCodigo()>0){
                       respuesta.println("<tr><td>" + librosRegistrados[i].getCodigo()+ "</td>");
                       respuesta.println("<td>" + librosRegistrados[i].getNombre_libro() + "</td>");
                       respuesta.println("<td>" + librosRegistrados[i].getTipo_de_pasta()+ "</td>");
                       respuesta.println("<td>" + librosRegistrados[i].getEditorial()+ "</td>");
                       respuesta.println("<td>" + librosRegistrados[i].getAnio_publicacion()+ "</td>");
                       respuesta.println("<td>"
                               + "<button type=\"button\" class=\"btn btn-warning\"></i>Editar</button> "
                               + "<button type=\"button\" class=\"btn btn-danger\" onclick=\"eliminarAlumno()\">Eliminar</button>"
                               + "</td></tr>");
                    }
                }
            //respuesta.println(1);
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
