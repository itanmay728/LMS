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

    let url = `http://localhost:8080/Caller/Caller_Dashboard/Leads/${query}`;

    fetch(url).then(response=>{

      return response.json();
    }).then((data)=>{
      
      //data...
     console.log(data);

      let text = `<div class = 'list-group'>`;


      data.forEach((leads) => {
        text+=`<a href='/Caller/Caller_Dashboard/Leads/edit/${leads.id}'  class='list-group-item list-group-action'> ${leads.email} <span class='float-end'> <b>Name:- </b> ${leads.name}  </span> </a>`
      });

      text += `</div>`;

      $(".search-result").html(text);
      $(".search-result").show();


    });
	}

};