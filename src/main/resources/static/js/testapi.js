/**
 * Created by STR02119 on 6/23/2017.
 */
function testapi() {
    console.log("test");
    $.ajax({
        type: "POST",
        url: "/api/test"
    }).done(function (data) {
        console.log(data);
    })
}