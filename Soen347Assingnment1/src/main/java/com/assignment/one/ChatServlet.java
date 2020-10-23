package com.assignment.one;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {


    /**
     * @see HttpServlet#HttpServlet()
     */

    public ChatServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Set refresh, autoload time as 30 seconds
        response.setIntHeader("Refresh", 30);

        String chats="";
        String actionNameReset="";


        for(Chat chat:ChatRepository.instance().getAllMessges())
            chats+=chat.user+":"+chat.message+"\n";

        request.setAttribute("chats",chats);

        request.getRequestDispatcher("index.jsp").forward(request, response);

        request.setAttribute("actionNameReset",actionNameReset);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionNameReset = request.getParameter("reset");
        String msg = request.getParameter("msg");
        String user = request.getParameter("user");
        //Date time;
       // time = request.getParameter(time);

        // if no username is entered show the user as Anonymous in the chat window
        if (user == null || user.isEmpty() )
                  user = "Anonymous";

        ChatRepository.instance().save(user, msg);

        if(actionNameReset != null )
            ChatRepository.instance().deleteAll();


        doGet(request,response);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ChatRepository.instance().deleteAll();
        doGet(req, resp);
    }

}