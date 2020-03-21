<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Главная страница</title>
</head>
<body>

<h1><b>Игроки</b></h1>

<p><b>Получить список игроков команды: </b><br>
  <form>
<p><input placeholder="Название клуба" name="club"></p>
<p><input type="submit" formaction="/client/players" value="Отправить запрос"></p>
</form>
</p>

<p><b>Добавить игрока в базу данных: </b><br>
  <form>
<p><input placeholder="Имя футболиста" name="name"></p>
<p><input type = "number" placeholder="Возраст" name="age"></p>
<p><input placeholder="Гражданство" name="country"></p>
<p><input placeholder="Позиция" name="position"></p>
<p><input placeholder="Название клуба" name="club"></p>
<p><input type="submit" formaction="/client/players" value="Отправить запрос" formmethod="post"></p>
</form>
</p>


<hr>
<h1><b>Клубы</b></h1>

<p><b>Получить список клубов: </b><br>
  <form>
<p><input type="submit" formaction="/client/clubs" value="Отправить запрос"></p>
</form>
</p>

<p><b>Добавить клуб в базу данных: </b><br>
  <form>
<p><input placeholder="Название клуба" name="name"></p>
<p><input type="submit" formaction="/client/clubs" value="Отправить запрос" formmethod="post"></p>
</form>
</p>

</body>
</html>

