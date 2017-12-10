function controllerTaskList() {
    $.ajax("/controllerTaskList", {
        method: "GET"
    }).done(function (response) {
        var table = "<table>" + "\n" + "<tbody>";

        for (var i = 0; i < response.length; i++){
            table +=  "<tr>" + "\n";
            table +=  "<th>" + response[i].title + "</th>" + "\n";
            table += "</tr>" + "\n";
        }
        table += "</tbody>"  + "\n" + "</table>";
        var div = document.querySelector('#taskTable');
        div.innerHTML = table;

        console.log(response);
    })

    setTimeout(controllerTaskList, 5000);
}

function addTaskToController(data) {

    var dataButton = data;
    console.log(dataButton);

    $.ajax("/addTaskToController", {
        method: "POST",
        contextType: "application/json",
        data: dataButton
    })
}

function buttonsGroupsComandsForRobots() {
    $.ajax("/allComandsForRobots", {
        method: "GET"
    }).done(function (response) {
        console.log(response);
        var buttonsGroups = "" + "\n";

        for (var i = 0; i < response.length; i++){
            buttonsGroups +=  "<button type=\"button\" class=\"btn btn-primary\" onclick='addTaskToController(\"" + response[i] + "\")'>"  + response[i] + "</button>" + "\n";
        }
        buttonsGroups += "\n";
        console.log(buttonsGroups);

        var div = document.querySelector('#buttonsGroupsComandsForRobots');

        div.innerHTML = buttonsGroups;
    })
}