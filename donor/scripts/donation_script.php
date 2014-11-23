<?php
include_once '../rest/include/DbHandler.php';

function drawrowsdonation(){

$handler=new DbHandler();
$result=$handler->getAllDonations();

while($row=$result->fetch_assoc()){
 echo '<tr class="rows" >';
  echo "<td>" . $row['firstname'] . "</td>";
  echo "<td>" . $row['lastname'] . "</td>";
  echo "<td>" . $row['name'] . "</td>";
  echo "<td>" . $row['time'] . "</td>";
  echo '</tr>';
}
}




?>