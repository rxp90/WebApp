window.onload = function() {

    //Get a reference to the link on the page
    // with an id of "mylink"
    var btnPuerto = document.getElementById("puerto");
    var btnHost = document.getElementById("host");
    //Set code to run when the link is clicked
    // by assigning a function to "onclick"
    btnPuerto.onclick = function() {
        var puerto = document.getElementById("puertoInput").value;
        if(validatePort(puerto)){
            document.getElementById("divPuerto").setAttribute("class", "control-group");
            document.getElementById("mensajePuerto").innerHTML = "";
            juego.setPuerto(puerto);
        }else{
            document.getElementById("divPuerto").setAttribute("class", "control-group error");
            document.getElementById("mensajePuerto").innerHTML = "Puerto no válido.";
        }
        return false;
    }
    btnHost.onclick = function() {
        var host = document.getElementById("hostInput").value;
        if(validateIP(host)){
            document.getElementById("divHost").setAttribute("class", "control-group");
            document.getElementById("mensajeHost").innerHTML = "";
            juego.setHost(host);
        }else{
            document.getElementById("divHost").setAttribute("class", "control-group error");
            document.getElementById("mensajeHost").innerHTML = "Dirección de IP no válida.";
        }
        return false;
    }
}
function validateIP(ip) { 
    var regex  = new RegExp("^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$");
    return regex.test(ip);
} 
function validatePort(puerto){
    var minPort = 1;
    var maxPort = 65535;
    return puerto.valueOf() > minPort && maxPort > puerto.valueOf();
}