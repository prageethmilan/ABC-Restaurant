import ApiService from "@src/services/apiService";

export async function getAllReservationsByQueryType(queryType, userId) {
  const apiObject = {}
  apiObject.method = "GET"
  apiObject.authentication = false
  apiObject.endpoint = `reservation/order/${queryType}/${userId}`
  apiObject.isBasicAuth = false
  apiObject.urlencoded = false
  return await ApiService.callApi(apiObject)
}

export async function saveReservation(reservationDetails) {
  const apiObject = {}
  apiObject.method = "POST"
  apiObject.authentication = false
  apiObject.endpoint = "reservation/table"
  apiObject.multipart = false
  apiObject.urlencoded = false
  apiObject.body = reservationDetails
  return await ApiService.callApi(apiObject)
}

export async function getAllReservations() {
  const apiObject = {}
  apiObject.method = "GET"
  apiObject.authentication = false
  apiObject.endpoint = `reservation`
  apiObject.isBasicAuth = false
  apiObject.urlencoded = false
  return await ApiService.callApi(apiObject)
}

export async function getAllReservationsByReservationType(reservationType) {
  const apiObject = {}
  apiObject.method = "GET"
  apiObject.authentication = false
  apiObject.endpoint = `table/${reservationType}`
  apiObject.isBasicAuth = false
  apiObject.urlencoded = false
  return await ApiService.callApi(apiObject)
}

export async function getAllReservationFromTableType(){
  const apiObject = {}
  apiObject.method = "GET"
  apiObject.authentication = false
  apiObject.endpoint = `reservation/TABLE`
  apiObject.multipart = false
  apiObject.urlencoded = false
  return await ApiService.callApi(apiObject)
}