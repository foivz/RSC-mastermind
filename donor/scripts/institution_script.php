<?php
include_once '../rest/include/DbHandler.php';

function drawrows(){

$handler=new DbHandler();
$result=$handler->getAllStations();

while($row=$result->fetch_assoc()){
 $id=$row['idstation'];
 echo '<tr class="rows" >';
  echo "<td>" . $row['name'] . "</td>";
  echo "<td>" . $row['adress'] . "</td>";
  echo "<td>" . $row['phone'] . "</td>";
  echo "<td>" . $row['city'] . "</td>";
  echo '</tr>';
}
}




?>