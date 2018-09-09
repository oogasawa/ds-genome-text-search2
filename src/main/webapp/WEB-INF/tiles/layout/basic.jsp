<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>DS genome text search</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>

  <style type="text/css">
  <!--
   #header {
    height: 200px;
    }

    img { border: 1px #0000ff solid; }


    .footer {
      position: absolute;
      bottom: 0;
      width: 100%;
      /* Set the fixed height of the footer here */
      height: 60px;
      background-color: #f5f5f5;
      /*background-color: "golden-oak";*/
    }
  //-->
  </style>
</head>

<body>

  <!-- Header -->
  <tiles:insertAttribute name="header" />

  <!-- Body -->
  <tiles:insertAttribute name="body" />

  <!-- Footer -->
  <tiles:insertAttribute name="footer" />

</body>
</html>
