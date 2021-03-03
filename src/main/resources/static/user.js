fetch('http://localhost:8080/userInfo')
    .then(response => response.json())
    .then(user => {
            $('#loginInfo').append(`${user.login}`)
            $('#rolesInfo').append(`${user.rolesToString}`)
            let temp = `<tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.age}</td>
                    <td>${user.login}</td>
                    <td>${user.rolesToString}</td>
                </tr>`
            $('#userInfo').append(temp)
    })