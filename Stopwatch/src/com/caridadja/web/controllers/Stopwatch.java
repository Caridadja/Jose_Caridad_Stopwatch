package com.caridadja.web.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import com.caridadja.web.models.Timer;
import java.time.*;

/**
 * Servlet implementation class Stopwatch
 */
@WebServlet("/Stopwatch")
public class Stopwatch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Stopwatch() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
				HttpSession session = request.getSession();
				Date currTime = new Date();
				session.setAttribute("currTime", currTime);
				System.out.println(session.getAttribute("startTime"));
				String command = request.getParameter("action");
				if(command !=null) {
					if(command.equals("reset")) {
						request.getSession().invalidate();
						
					}
				}
				if(session.getAttribute("times")==null) {
					session.setAttribute("times", new ArrayList<Timer>());
				}
				if(command !=null) {
					if(command.equals("start")) {
						if(session.getAttribute("startTime")==null) {
							Date start = new Date();
							session.setAttribute("startTime", start);
						}
						
					}else if(command.equals("stop")) {
						if(session.getAttribute("startTime")!=null) {
							Timer newTime = new Timer((Date) session.getAttribute("startTime"), currTime);
							session.setAttribute("startTime", null);
							session.setAttribute("endTime", null);
							ArrayList<Timer> times = (ArrayList<Timer>) session.getAttribute("times");
							times.add(newTime);
							session.setAttribute("times", times);
						}
					}
				}
				if(session.getAttribute("startTime")!=null) {
					long difference = currTime.getTime() - ((Date)session.getAttribute("startTime")).getTime();
					session.setAttribute("difference", difference);
				}
				
				
				
				request.getRequestDispatcher("/WEB-INF/views/stopwatch.jsp").forward(request, response);
			}

			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// TODO Auto-generated method stub
				
				doGet(request, response);
			}

}
