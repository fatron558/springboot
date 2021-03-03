function edit(userId){
    fetch(`http://localhost:8080/admin/${userId}`)
        .then(response => response.json())
        .then(user => {
            $('#userIdEdit').attr('value', `${user.id}`)
            $('#userFirstNameEdit').attr('value', `${user.firstName}`)
            $('#userLastNameEdit').attr('value', `${user.lastName}`)
            $('#userAgeEdit').attr('value', `${user.age}`)
            $('#userLoginEdit').attr('value', `${user.login}`)
            $('#userPasswordEdit').attr('value', `${user.password}`)
            $('#btnEdit').attr('onclick', `editUser()`)
        })
}

function editUser(){
    $('#editUserRole').attr('value', [])
    let role = $('#editUserRole').val();
    fetch('http://localhost:8080/admin',{
        method: 'PUT',
        body: JSON.stringify({
            id: $('#userIdEdit').val(),
            firstName: $('#userFirstNameEdit').val(),
            lastName: $('#userLastNameEdit').val(),
            age: $('#userAgeEdit').val(),
            login: $('#userLoginEdit').val(),
            password: $('#userPasswordEdit').val(),
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

