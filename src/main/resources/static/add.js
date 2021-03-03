function addNewUser() {
    let role = $('#userRoleNew').val();
    fetch('http://localhost:8080/admin', {
        method: 'POST',
        body: JSON.stringify({
            firstName: $('#userFirstNameNew').val(),
            lastName: $('#userLastNameNew').val(),
            age: $('#userAgeNew').val(),
            login: $('#userLoginNew').val(),
            password: $('#userPasswordNew').val(),
            roles:
                [
                    {
                        id: null,
                        role: role[0]
                    },
                    {
                        id: null,
                        role: role[1]
                    }
                ]
        }),
        headers: {'Content-type': 'application/json; charset=UTF-8'},
    }).then(showAllUsers)
}