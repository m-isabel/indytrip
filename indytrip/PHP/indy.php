<?php
define("DB_HOST", "mysql9.000webhost.com");
define("DB_USER", "a5542495_xtern");
define("DB_PASSWORD", "xtern2014");
define("DB_NAME", "a5542495_indy");
$connection = mysql_connect(DB_HOST,DB_USER,DB_PASSWORD) or die('Unable to connect');
//echo "connection done";
$db = mysql_selectdb(DB_NAME,$connection) or die('Database not found');
if($db)
{


    $NoOfDays=$_GET['NoOfDays'];

    if($NoOfDays==1){
        $AverageRating="5";
    }
    else if($NoOfDays==2){
        $AverageRating="4";
    }
    else if($NoOfDays==3){
        $AverageRating="3";
    }
    else{
      $AverageRating="2";
    }
    //echo "\n";
    //echo $AverageRating;
    $NoOfDestinations= $NoOfDays *4;
  
 
    $HotelName=$_GET['HotelName'];
    $HotelCost="200";
    

    $TripBudget=$_GET['budget'];

    $Art=$_GET['art'];
    $History=$_GET['history'];
    $Outdoor=$_GET['outdoor'];
    $Sports=$_GET['sports'];
    $Cuisine=$_GET['food'];
    $Culture=$_GET['culture'];
    $Music="1";
    $Night=$_GET['nightlife'];
    

  
    $sql="INSERT INTO Users VALUES('','$NoOfDays', '$NoOfDestinations','$HotelName','$HotelCost','$TripBudget','$Art','$Culture','$History','$Sports','$Outdoor','$Food','$Music','$Night')";
    $result=mysql_query($sql);

     //From Art table
        $data = mysql_query("SELECT * FROM Art where Rating>='$AverageRating'");
        if ($data) {
          while ($row = mysql_fetch_assoc($data)){
           $PlaceName=$row['PlaceName'];
           $Description=$row['Description'];
           $AllotedTime=$row['AllotedTime'];
           $Rating=$row['Rating'];
           $Address=$row['Address'];
           $Lat=$row['Lat'];
           $Lon=$row['Lon'];
           $Cost=$row['Cost'];
           $Price=$row['Price'];
           $Website=$row['Website'];
           $Duration=$row['Duration'];
           $MustSee=$row['MustSee'];
           $Category=$row['Category'];

           $sql11= mysql_query("INSERT INTO Temp VALUES('','$PlaceName','$Description','$AllotedTime','$Rating','$Address','$Lat','$Lon','$Cost','$Price','$Website','$Duration','$MustSee','$Category')");


       }

   }

        //From History table
        $data = mysql_query("SELECT * FROM History where Rating>='$AverageRating'");
        if ($data) {
          while ($row = mysql_fetch_assoc($data)){
           $PlaceName=$row['PlaceName'];
           $Description=$row['Description'];
           $AllotedTime=$row['AllotedTime'];
           $Rating=$row['Rating'];
           $Address=$row['Address'];
           $Lat=$row['Lat'];
           $Lon=$row['Lon'];
           $Cost=$row['Cost'];
           $Price=$row['Price'];
           $Website=$row['Website'];
           $Duration=$row['Duration'];
           $MustSee=$row['MustSee'];
           $Category=$row['Category'];

           $sql11= mysql_query("INSERT INTO Temp VALUES('','$PlaceName','$Description','$AllotedTime','$Rating','$Address','$Lat','$Lon','$Cost','$Price','$Website','$Duration','$MustSee','$Category')");


       }

   }

        //From Sports table
        $data = mysql_query("SELECT * FROM Sports where Rating>='$AverageRating'");
        if ($data) {
          while ($row = mysql_fetch_assoc($data)){
           $PlaceName=$row['PlaceName'];
           $Description=$row['Description'];
           $AllotedTime=$row['AllotedTime'];
           $Rating=$row['Rating'];
           $Address=$row['Address'];
           $Lat=$row['Lat'];
           $Lon=$row['Lon'];
           $Cost=$row['Cost'];
           $Price=$row['Price'];
           $Website=$row['Website'];
           $Duration=$row['Duration'];
           $MustSee=$row['MustSee'];
           $Category=$row['Category'];

           $sql11= mysql_query("INSERT INTO Temp VALUES('','$PlaceName','$Description','$AllotedTime','$Rating','$Address','$Lat','$Lon','$Cost','$Price','$Website','$Duration','$MustSee','$Category')");


       }

   }
        //From Cuisine table
        $data = mysql_query("SELECT * FROM Cuisine where Rating>='$AverageRating'");
        if ($data) {
          while ($row = mysql_fetch_assoc($data)){
           $PlaceName=$row['PlaceName'];
           $Description=$row['Description'];
           $AllotedTime=$row['AllotedTime'];
           $Rating=$row['Rating'];
           $Address=$row['Address'];
           $Lat=$row['Lat'];
           $Lon=$row['Lon'];
           $Cost=$row['Cost'];
           $Price=$row['Price'];
           $Website=$row['Website'];
           $Duration=$row['Duration'];
           $MustSee=$row['MustSee'];
           $Category=$row['Category'];

           $sql11= mysql_query("INSERT INTO Temp VALUES('','$PlaceName','$Description','$AllotedTime','$Rating','$Address','$Lat','$Lon','$Cost','$Price','$Website','$Duration','$MustSee','$Category')");


       }

   }
    //From Culture table
        $data = mysql_query("SELECT * FROM Culture where Rating>='$AverageRating'");
        if ($data) {
          while ($row = mysql_fetch_assoc($data)){
           $PlaceName=$row['PlaceName'];
           $Description=$row['Description'];
           $AllotedTime=$row['AllotedTime'];
           $Rating=$row['Rating'];
           $Address=$row['Address'];
           $Lat=$row['Lat'];
           $Lon=$row['Lon'];
           $Cost=$row['Cost'];
           $Price=$row['Price'];
           $Website=$row['Website'];
           $Duration=$row['Duration'];
           $MustSee=$row['MustSee'];
           $Category=$row['Category'];

           $sql11= mysql_query("INSERT INTO Temp VALUES('','$PlaceName','$Description','$AllotedTime','$Rating','$Address','$Lat','$Lon','$Cost','$Price','$Website','$Duration','$MustSee','$Category')");


       }

   }
    //From Music table
        $data = mysql_query("SELECT * FROM Music where Rating>='$AverageRating'");
        if ($data) {
          while ($row = mysql_fetch_assoc($data)){
           $PlaceName=$row['PlaceName'];
           $Description=$row['Description'];
           $AllotedTime=$row['AllotedTime'];
           $Rating=$row['Rating'];
           $Address=$row['Address'];
           $Lat=$row['Lat'];
           $Lon=$row['Lon'];
           $Cost=$row['Cost'];
           $Price=$row['Price'];
           $Website=$row['Website'];
           $Duration=$row['Duration'];
           $MustSee=$row['MustSee'];
           $Category=$row['Category'];

           $sql11= mysql_query("INSERT INTO Temp VALUES('','$PlaceName','$Description','$AllotedTime','$Rating','$Address','$Lat','$Lon','$Cost','$Price','$Website','$Duration','$MustSee','$Category')");


       }

   }
    //From Night table
        $data = mysql_query("SELECT * FROM Night where Rating>='$AverageRating'");
        if ($data) {
          while ($row = mysql_fetch_assoc($data)){
           $PlaceName=$row['PlaceName'];
           $Description=$row['Description'];
           $AllotedTime=$row['AllotedTime'];
           $Rating=$row['Rating'];
           $Address=$row['Address'];
           $Lat=$row['Lat'];
           $Lon=$row['Lon'];
           $Cost=$row['Cost'];
           $Price=$row['Price'];
           $Website=$row['Website'];
           $Duration=$row['Duration'];
           $MustSee=$row['MustSee'];
           $Category=$row['Category'];

           $sql11= mysql_query("INSERT INTO Temp VALUES('','$PlaceName','$Description','$AllotedTime','$Rating','$Address','$Lat','$Lon','$Cost','$Price','$Website','$Duration','$MustSee','$Category')");


       }

   }
    //From Outdoor table
        $data = mysql_query("SELECT * FROM Outdoor where Rating>='$AverageRating'");
        if ($data) {
          while ($row = mysql_fetch_assoc($data)){
           $PlaceName=$row['PlaceName'];
           $Description=$row['Description'];
           $AllotedTime=$row['AllotedTime'];
           $Rating=$row['Rating'];
           $Address=$row['Address'];
           $Lat=$row['Lat'];
           $Lon=$row['Lon'];
           $Cost=$row['Cost'];
           $Price=$row['Price'];
           $Website=$row['Website'];
           $Duration=$row['Duration'];
           $MustSee=$row['MustSee'];
           $Category=$row['Category'];

           $sql11= mysql_query("INSERT INTO Temp VALUES('','$PlaceName','$Description','$AllotedTime','$Rating','$Address','$Lat','$Lon','$Cost','$Price','$Website','$Duration','$MustSee','$Category')");


       }

   }


//filtering based on budget
if($TripBudget==5){
    $sql=mysql_query("DELETE from Temp where Price < 2");
}
elseif($TripBudget==4){
    $sql=mysql_query("DELETE from Temp where Price=5");
}
elseif($TripBudget==3){
    $sql=mysql_query("DELETE from Temp where Price>=4");
}
elseif($TripBudget==2){
    $sql=mysql_query("DELETE from Temp where Price>=3");
}
elseif($TripBudget==1){
    $sql=mysql_query("DELETE from Temp where Price>2");
}
//Selecting which category has more destinations than other. Sorting user's preference.
$preference = array(

   array("type"=>"Art", "rate"=>$Art),
   array("type"=>"History", "rate"=>$History),
   array("type"=>"Culture", "rate"=>$Culture),
   array("type"=>"Sports", "rate"=>$Sports),
   array("type"=>"Music", "rate"=>$Music),
   array("type"=>"Outdoor", "rate"=>$Outdoor)

);

//Sorting in descending order
$rate = array();
foreach ($preference as $key => $row)
{
    $rate[$key] = $row['rate'];
}
array_multisort($rate, SORT_DESC, $preference);

//Puttin it in different variables
$Like1=$preference[0]['type'];
$pre1=$preference[0]['rate'];

if($pre1==5){
    $dest1=$NoOfDestinations/2;
}

$Like2=$preference[1]['type'];
$pre2=$preference[1]['rate'];
if($pre2==5){
    $dest2=$NoOfDestinations/2;
}

$Like3=$preference[2]['type'];
$pre3=$preference[2]['rate'];


$Like4=$preference[3]['type'];
$pre4=$preference[3]['rate'];


$Like5=$preference[4]['type'];
$pre5=$preference[4]['rate'];

$Like6=$preference[5]['type'];
$pre6=$preference[5]['rate'];
echo $Like1,$Like2,$Like3,$Like4,$Like5,$Like6;
echo "\n";
//Selecting morning destination and populating final itinery table
do{
    echo "Filling final table. fingers crossed. \n";
    //Fill morning destinations.
    $count=$NoOfDays;
    $sql= mysql_query("select * from Temp where Category='$Like1' AND Rating>='$AverageRating'");
    $info = mysql_fetch_array( $sql);

    $Duration1=$info['Duration'];
    $PlaceName1=$info['PlaceName'];
    echo $PlaceName1;
    $EndTime1=10.30+$Duration1;
    $MorningTime1="10.30AM - ".$EndTime1."AM";
    if($sql){
        $sql2=mysql_query("INSERT INTO Final VALUES('$count','7:00AM','$PlaceName1','$MorningTime1','','1-2PM','','','','6.30PM - 8PM','','')");
    }
    $sql= mysql_query("Delete from Temp where PlaceName='$PlaceName1'");
    $RemainingTime=2-$Duration1;
    if($RemainingTime>=1){ 
       $sql1= mysql_query("select * from Temp where Rating>='$AverageRating' AND Duration='1' And Category='$Like2'");
       if (mysql_num_rows($sql1) == 0) {
        $sql1= mysql_query("select * from Temp where Rating>='$AverageRating' AND Duration='1' And Category='$Like3'");
      }
      if (mysql_num_rows($sql1) == 0) {
       $sql1= mysql_query("select * from Temp where Rating>='$AverageRating' AND Duration='1' And Category='$Like4'");
     }
     if(mysql_num_rows($sql1) == 0){
      $sql1= mysql_query("select * from Temp where Rating>='$AverageRating' AND Duration='1' And Category='$Like5'");
    }

    $info1 = mysql_fetch_array( $sql1);
    $Duration2=$info1['Duration'];
    $PlaceName2=$info1['PlaceName'];
    $EndTime2=$EndTime1+$Duration2;
    $MorningTime2=$EndTime1."AM - ".$EndTime2."AM";
    if($sql1){
        $sql=mysql_query("INSERT INTO Final VALUES('$count','7:00AM','$PlaceName2','$MorningTime2','','1-2PM','','','','6.30PM - 8PM','','')");
        }
    $sql= mysql_query("Delete from Temp where PlaceName='$PlaceName2'");
    }
    echo "morning is decided \n";
return;

    //Fill lunch place column
    $sql=mysql_query("select * from Temp where AllotedTime='Lunch' and Rating<=$Cuisine");
    $info = mysql_fetch_array( $sql);
    $LunchPlace=$info['PlaceName'];
    if($sql){
        $sql3=mysql_query("update Final set Lunch='$LunchPlace' where Day='$count'");
    }
    $sql= mysql_query("Delete from Temp where PlaceName='$LunchPlace'");

    echo "Filling final table. Afternoon entries \n";

    //Fill afternoon destinations.
    $sql= mysql_query("select * from Temp where Duration='2' and Rating='5' and Category='$Like2'");
    if (mysql_num_rows($sql) == 0) {
            $sql= mysql_query("select * from Temp where Rating>='4' AND Duration='2' And Category='$Like3'");
            }
            if (mysql_num_rows($sql) == 0) {
               $sql= mysql_query("select * from Temp where Rating>='4' AND Duration='2' And Category='$Like4'");
                }
                if(mysql_num_rows($sql) == 0){
                    $sql= mysql_query("select * from Temp where Rating>='4' AND Duration='2' And Category='$Like5'");
                }
              /*  else{
                      $sql=mysql_query("INSERT INTO Final VALUES('$count','7:00AM','Free time. Explore the time.','11.30AM - 12.30PM','','1-2PM','','','','6.30PM - 8PM','','')");
                }*/

    $info = mysql_fetch_array($sql);
    $Duration3=$info['Duration'];
    $PlaceName3=$info['PlaceName'];
    echo "";
    echo "";
    echo $PlaceName3;

    $EndTime3=2.45+$Duration3;
    $AfternoonTime="2.45PM - ".$EndTime3."PM";
    echo $AfternoonTime;
       $sql3=mysql_query("update Final set AfterDest='$PlaceName3' where Day='$count'");
           $sql4=mysql_query("update Final set AfterTime='$AfternoonTime' where Day='$count'");
    
    $sql= mysql_query("Delete from Temp where PlaceName='$PlaceName3'");
    $RemainingTime=3-$Duration3;
    if($RemainingTime>=1){
       $sql1= mysql_query("select * from Temp where Rating>='4' AND Duration='1' And Category='$Like3'");
        if (mysql_num_rows($sql1) == 0) {
            $sql1= mysql_query("select * from Temp where Rating>='4' AND Duration='1' And Category='$Like4'");
            }
            if (mysql_num_rows($sql1) == 0) {
               $sql1= mysql_query("select * from Temp where Rating>='4' AND Duration='1' And Category='$Like5'");
                }
                if(mysql_num_rows($sql1) == 0){
                    $sql1= mysql_query("select * from Temp where Rating>='4' AND Duration='1' And Category='$Like1'");
                }
              /*  else{
                      $sql=mysql_query("INSERT INTO Final VALUES('$count','7:00AM','Free time. Explore the time.','11.30AM - 12.30PM','','1-2PM','','','','6.30PM - 8PM','','')");
                }
                */
    $info1 = mysql_fetch_array( $sql1);
    $Duration4=$info1['Duration'];
    $PlaceName4=$info1['PlaceName'];
    $EndTime3=$EndTime3+0.30;
    $EndTime4=$EndTime3+$Duration4;
    $AfternoonTime2=$EndTime3."PM - ".$EndTime4."PM";
    if($sql1){
        $sql=mysql_query("update Final set AfternoonDest='$PlaceName2' and AfternoonTime='$AfternoonTime2' where Day='$count'");
        }
    $sql= mysql_query("Delete from Temp where PlaceName='$PlaceName4'");
    }
    echo "Afternoon entries decided.";

    //Filling dinner place
     $sql=mysql_query("select * from Temp where AllotedTime='Dinner' and Rating>$Cuisine");
    $info = mysql_fetch_array($sql);
    $DinnerPlace=$info['PlaceName'];
    if($sql){
        $sql3=mysql_query("update Final set Dinner='$DinnerPlace' ");
    }
    $sql= mysql_query("Delete from Temp where PlaceName='$DinnerPlace'");
    echo "Dinner decided";
    //If interested in night life
    if($Night>2){
        $Nightlife=true;
        $sql=mysql_query("select * from Temp where AllotedTime='Night' and Rating>=$night");
        $info = mysql_fetch_array($sql);
        $NightPlace=$info['PlaceName'];
        if($sql){
            $sql3=mysql_query("update Final set Night='$NightPlace'");
            $sql3=mysql_query("update Final set NightTime='8.30PM - 10.00PM'");
        }
    $sql= mysql_query("Delete from Temp where PlaceName='$NightPlace'");
    }
    else{
        $Nightlife=false;
        }

    $count=$count-1;
}while($count>0);
    $sql= mysql_query("Truncate table Temp");
}
mysql_close($connection);

?>