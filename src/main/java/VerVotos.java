import modelo.Jugador;

import java.io.*;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class VerVotos extends HttpServlet {

    private ModeloDatos bd;

    @Override
    public void init(ServletConfig cfg) throws ServletException {
        bd = new ModeloDatos();
        bd.abrirConexion();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession s = req.getSession(true);
        List<Jugador> jugadores = bd.getJugadores();
        s.setAttribute("jugadores", jugadores);
        res.sendRedirect(res.encodeRedirectURL("VerVotos.jsp"));
    }

    @Override
    public void destroy() {
        bd.cerrarConexion();
        super.destroy();
    }
}
