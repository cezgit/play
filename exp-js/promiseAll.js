
// First promise returns an array after a delay
const getUsers = () => {
  return new Promise((resolve, reject) => {
    return setTimeout(
      () => resolve([{ id: 'jon' }, { id: 'andrey' }, { id: 'tania' }]),
      600
    )
  })
}

// Second promise relies on the result of first promise
const getIdFromUser = (user) => {
  return new Promise((resolve, reject) => {
    return setTimeout(() => resolve(user.id), 500)
  })
}

// Third promise relies on the result of the second promise and turns the ID to upper case
const capitalizeIds = (id) => {
  return new Promise((resolve, reject) => {
    return setTimeout(() => resolve(id.toUpperCase()), 200)
  })
}

// use Promise.all - waits until all promises either resolved or rejected

const runAsyncFunctions = async () => {
  const users = await getUsers()

  Promise.all(
    users.map(async (user) => {

      const userId = await getIdFromUser(user)
      console.log(userId)

      const capitalizedId = await capitalizeIds(userId)
      console.log(capitalizedId)
    })
  )

  console.log(users)
}

runAsyncFunctions()
