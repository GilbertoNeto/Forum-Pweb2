<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Page</title>
</head>
<body>

<div class="log-form">
  <h2>J� � usu�rio? Fa�a o login abaixo</h2>
  <form action="loginServlet" method="">
    <input type="text" title="username" placeholder="nome de usu�rio ou email" />
    <input type="password" title="username" placeholder="senha" />
    
    <button type="submit" class="btn">Login</button>
    
    <br><br>
    
    <h2>N�o � usu�rio? Crie sua conta agora</h2>
    <a class="forgot" href="Cadastro.jsp">Criar Conta</a>
    
    
  </form>
</div>

</body>
</html>