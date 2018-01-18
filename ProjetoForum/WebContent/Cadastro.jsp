<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forum Java Web</title>
</head>
<body>

     <h1>Formulário para cadastro de usuário</h1>
     
     
     <form action="cadastro" method="post">
         
   		<label>Nome de usuário: </label>
         <input type="text" name="nome"  required/>
         <br><br>
         
   		<label>E-mail: </label>
   		<input type="text" name="email" required />
   		<br><br>
   		
   		<label>Senha:</label>
   		<input type="password" name="senha" placeholder="8 digitos" required />
   		<br><br>
   		
   	<button>Cadastrar</button>
   </form>
    


</body>
</html>