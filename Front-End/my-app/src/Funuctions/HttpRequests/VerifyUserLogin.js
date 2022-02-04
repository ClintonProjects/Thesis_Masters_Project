class VerifyUserLogin {
}

VerifyUserLogin.verify = function () {
    const requestOptions = {
        method: "GET",
        headers: {
            'Content-Type': 'application/json',
            'Accept': 'application/json',
            "Access-Control-Allow-Origin": "*",
        },
    };
        fetch('http://localhost:8085/login/session/' + localStorage.getItem('SessionId'), requestOptions)
            .then((response) => response.json())
            .then((messages) => {
                 console.log(messages.response);
                 return messages.response;
            });
}



module.exports = {
    functions: VerifyUserLogin 
};