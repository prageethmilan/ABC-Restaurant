// ** Returns paginated array
export const paginateArray = (array, perPage, page) => array.slice((page - 1) * perPage, page * perPage)

// ** Returns sorted array
export const sortCompare = key => (a, b) => {
  const fieldA = a[key]
  const fieldB = b[key]

  let comparison = 0
  if (fieldA > fieldB) {
    comparison = 1
  } else if (fieldA < fieldB) {
    comparison = -1
  }
  return comparison
}

// eslint-disable-next-line no-mixed-operators
export const nextDay = new Date(new Date().getTime() + 24 * 60 * 60 * 1000)
// eslint-disable-next-line no-mixed-operators
export const nextWeek = new Date(nextDay.getTime() + 7 * 24 * 60 * 60 * 1000)

// ** Returns number range
export const getRandomInt = (min, max) => {
  if (min > max) {
    const temp = max
    /* eslint-disable no-param-reassign */
    max = min
    min = temp
    /* eslint-enable */
  }

  if (min <= 0) {
    return Math.floor(Math.random() * (max + Math.abs(min) + 1)) + min
  }
  return Math.floor(Math.random() * max) + min
}

// ** Returns random date
export const randomDate = (start, end) => {
  const diff = end.getTime() - start.getTime()
  const newDiff = diff * Math.random()
  const date = new Date(start.getTime() + newDiff)
  return date
}
