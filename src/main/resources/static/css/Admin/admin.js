// Graph
//alert("js is activeted")

//searching users/ employees func

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

// searching for all leads under all leads in admin dashboard

const searchLead = () => {
    let query = $("#search-input").val();

    if (query == "") {
        $(".search-result").hide();
    } else {
        let url = `http://localhost:8080/Admin/admin_Dashboard/Leads/search/${query}`;

        fetch(url).then(response => {
            return response.json();
        }).then((data) => {
            let text = `<div class='list-group'>`;

            data.forEach((lead) => {
                text += `<a href='#' class='list-group-item list-group-action' onclick='scrollToLead(${lead.id})'>${lead.email}<span class='float-end'><b>Name:-</b> ${lead.name}</span></a>`;
            });

            text += `</div>`;

            $(".search-result").html(text);
            $(".search-result").show();
        });
    }
};

const scrollToLead = (leadId) => {
    let element = document.getElementById(`lead-${leadId}`);
    element.scrollIntoView({ behavior: 'smooth' });
    element.style.backgroundColor = "yellow"; // Highlight the lead row
    setTimeout(() => {
        element.style.backgroundColor = ""; // Remove the highlight after a few seconds
    }, 1000);
};

//searching business associates under approved business associates

const searchBusinessAssociate = () => {
	//console.log("searching...");

	let query = $("#search-input").val();

	if (query == "") {
		$(".search-result").hide();
	} else {
		//search
		console.log(query);

    //sending request to server

    let url = `http://localhost:8080/Admin/admin_Dashboard/BusinessAssociate/search/${query}`;

    fetch(url).then(response=>{

      return response.json();
    }).then((data) => {
            let text = `<div class='list-group'>`;

            data.forEach((businessAssociate) => {
                text += `<a href='#' class='list-group-item list-group-action' onclick='scrollToBA(${businessAssociate.id})'>${businessAssociate.userName}<span class='float-end'><b>Name:-</b> ${businessAssociate.name}</span></a>`;
            });

            text += `</div>`;

            $(".search-result").html(text);
            $(".search-result").show();
        });
    }
};

const scrollToBA = (businessAssociateId) => {
    let element = document.getElementById(`businessAssociate-${businessAssociateId}`);
    element.scrollIntoView({ behavior: 'smooth' });
    element.style.backgroundColor = "yellow"; // Highlight the lead row
    setTimeout(() => {
        element.style.backgroundColor = ""; // Remove the highlight after a few seconds
    }, 1000);
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

