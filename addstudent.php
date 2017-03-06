<?php
	$connect =mysqli_connect("localhost","root","","student");

	$id =$_POST['id'];
	$name=$_POST['name'];

	$sql ="insert into student (id,name) values ('$id','$name')";
	$result =mysqli_query($connect,$sql);
	mysqli_close($connect);

	if($result){
		print(json_encode('1'));
	}else{
		print(json_encode('2'));
	}
	
	
?>