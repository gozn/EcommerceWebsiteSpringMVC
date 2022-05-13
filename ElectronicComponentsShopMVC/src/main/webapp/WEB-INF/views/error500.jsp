<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/WEB-INF/views/tablib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
@import url("https://fonts.googleapis.com/css?family=Fira+Code&display=swap");

* {
  margin: 0;
  padding: 0;
  font-family: "Fira Code", monospace;
}
body {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #ecf0f1;
}

.container {
  text-align: center;
  margin: auto;
  padding: 4em;
  img {
    width: 256px;
    height: 225px;
  }

  h1 {
    margin-top: 1rem;
    font-size: 35px;
    text-align: center;

    span {
      font-size: 60px;
    }
  }
  p {
    margin-top: 1rem;
  }

  p.info {
    margin-top: 4em;
    font-size: 12px;

    a {
      text-decoration: none;
      color: rgb(84, 84, 206);
    }
  }
}

</style>
<meta charset="UTF-8">
<title>404!</title>
</head>
<body>
<div>

<div class="container">
      <img src="https://i.imgur.com/qIufhof.png" />

      <h1>
        <span>500</span> <br />
        Internal server error
      </h1>
      <p>We are currently trying to fix the problem.</p>
      <p class="info">
      </p>
    </div>
</div>
  
<script>

</script>
</body>
</html>