import abir.shah.messageBoardSystem.usecase.AuthenticateUserUsecase;

import javax.servlet.http.HttpServletRequest;

public class Authentication {


    public static void authenticateUser(HttpServletRequest request)
    {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        AuthenticateUserUsecase userUsecase = new AuthenticateUserUsecase();

        if(!userUsecase.isAuthenticated(userId,password))
            throw new RuntimeException("user is not authenticated with "+userId +"  "+password);
    }

    public static String extractUserId(HttpServletRequest request)
    {
        return request.getHeader("user-id");
    }

}
