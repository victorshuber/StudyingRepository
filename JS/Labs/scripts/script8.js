var name = "admin";
var pass = "admin777";
var name_ent = prompt("Enter your name: ");

if (name_ent != "") {
    if (name_ent == name) {
        var pass_ent = prompt("Enter your password: ");
        if (pass_ent != "") {
            if (pass_ent == pass)
                alert("Hello");
            else
                alert("Incorrect password");
        } else
            alert("Validation error");

    } else
        alert("Incorrect username");
} else
    alert("Validation error");