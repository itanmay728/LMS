// Graph
//alert("js is activeted")

//searching func

const search = () => {
	//console.log("searching...");

	let query = $("#search-input").val();

	if (query == "") {
		$(".search-result").hide();
	} else {
		//search
		console.log(query);

    //sending request to server

    let url = `http://localhost:8080/Admin/admin_Dashboard/users/search/${query}`;

    fetch(url).then(response=>{

      return response.json();
    }).then((data)=>{
      
      //data...
     console.log(data);

      let text = `<div class = 'list-group'>`;


      data.forEach((employee) => {
        text+=`<a href='/Admin/admin_Dashboard/users/${employee.id}'  class='list-group-item list-group-action'> ${employee.userName} <span class='float-end'> <b>Name:- </b> ${employee.name}  </span> </a>`
      });

      text += `</div>`;

      $(".search-result").html(text);
      $(".search-result").show();


    });
	}

};

var ctx = document.getElementById("myChart");

var myChart = new myChart(ctx, {
	type: "line",
	data: {
		labels: [
			"Sunday",
			"Monday",
			"Tuesday",
			"Wednesday",
			"Thursday",
			"Friday",
			"Saturday",
		],
		datasets: [
			{
				data: [15339, 21345, 18483, 24003, 23489, 24092, 12034],
				lineTension: 0,
				backgroundColor: "transparent",
				borderColor: "#007bff",
				borderWidth: 4,
				pointBackgroundColor: "#007bff",
			},
		],
	},
	options: {
		scales: {
			yAxes: [
				{
					ticks: {
						beginAtZero: false,
					},
				},
			],
		},
		legend: {
			display: false,
		},
	},
});

