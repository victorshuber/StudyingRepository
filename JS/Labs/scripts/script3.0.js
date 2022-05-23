var a = 19;
var b = 43;
var c = 7;
var arr = [a, b, c];

arr.sort(function(a, b) { return a - b });
alert('Min number: ' + arr[0] + " & max number:" + arr[arr.length - 1]);