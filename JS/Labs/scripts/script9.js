var name = prompt("Enter your name: ");

if (name != "") {
    alert("Hello " + name)
    var res = confirm("Do you know JavaScript?");
    if (res)
        alert("Great!");
    else
        alert("We can start to learn it now");
} else
    alert("Validation error");