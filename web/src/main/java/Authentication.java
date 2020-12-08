import abir.shah.messageBoardSystem.domain.entity.User;
import abir.shah.messageBoardSystem.usecase.AuthenticateUserUsecase;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class Authentication {


    public static String extractUserIdFromSession(HttpServletRequest request)
    {
        Cookie[] cookies = request.getCookies();

        if(cookies!=null && cookies.length>1)
            System.out.println("session "+cookies[0].getName() +"  value "+cookies[0].getValue());

        if(cookies==null)
            throw new RuntimeException("user is not authenticated");

        for( Cookie cookie : cookies)
        {
            if(cookie.getName().equals("session"))
            {
                AuthenticateUserUsecase userUsecase = new AuthenticateUserUsecase();
                User user = userUsecase.decryptToken(cookie.getValue());

                return  user.getId();
            }
        }

        throw new RuntimeException("user is not authenticated");
    }

}
