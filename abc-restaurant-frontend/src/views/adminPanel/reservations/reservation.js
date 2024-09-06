import React, { useState, useEffect } from "react"
import ReactPaginate from "react-paginate"
import DataTable from "react-data-table-component"
import {
  Card,
  CardHeader,
  CardTitle,
  Input,
  Badge,
  Modal,
  ModalHeader,
  ModalBody,
  Button,
  Row,
  Col,
  Label
} from "reactstrap";
import moment from "moment"
import {
  getAllReservationFromTableType, getReservationQueriesById, saveQueryForReservation
} from "@src/services/reservation";
import './reservation.scss'
import { Assets } from "@src/assets/images"
import { getBadgeColor, isUserLoggedIn } from "@utils"
import { useTranslation } from "react-i18next";
import { USER_ROLES } from "@src/const/const";

const Reservation = () => {
  const [currentPage, setCurrentPage] = useState(0)
  const [searchValue, setSearchValue] = useState("")
  const [filteredData, setFilteredData] = useState([])
  const [data, setData] = useState([])
  const [modal, setModal] = useState(false)
  const [selectedReservationId, setSelectedReservationId] = useState(null)
  const [queries, setQueries] = useState([])
  const [replyMessage, setReplyMessage] = useState("")
  const [userData, setUserData] = useState(null)
  const { t } = useTranslation()

  useEffect(() => {
    if (isUserLoggedIn() !== null) {
      setUserData(JSON.parse(localStorage.getItem("USER_OBJECT")))
    }
  }, [])

  const columns = [
    {
      name: "Res-Id",
      selector: row => row.reservation.id,
      sortable: false,
      minWidth: "150px"
    },
    {
      name: "Table Type",
      selector: row => row.reservation.tableReservationType,
      sortable: false,
      minWidth: "160px"
    },
    {
      name: "Max Count",
      selector: row => row.reservation.maxCount,
      sortable: false,
      minWidth: "125px"
    },
    {
      name: "Reserved Date",
      selector: row => row.reservation.reservedDate,
      sortable: false,
      minWidth: "180px",
      cell: row => moment(row.reservation.reservedDate).format("YYYY-MM-DD  h:mm a")
    },
    {
      name: "Operation Status",
      selector: row => row.reservation.status,
      sortable: false,
      minWidth: "160px",
      cell: row => (
        <Badge color={getBadgeColor(row.reservation.operationalStatus)} className="badge-glow">
          {row.reservation.operationalStatus}
        </Badge>
      )
    },
    {
      name: "Created Date",
      selector: row => row.reservation.createdDate,
      sortable: false,
      minWidth: "180px",
      cell: row => moment(row.reservation.createdDate).format("YYYY-MM-DD  h:mm a")
    },
    {
      name: "Actions",
      allowOverflow: true,
      minWidth: "250px",
      cell: row => (
        <div>
          <Button color="primary" size="sm" onClick={() => handleRespond(row.reservation.id)}>Query</Button>
          <Button color="success" size="sm" onClick={() => handleApprove(row.reservation.id)} className="ms-2">Approve</Button>
        </div>
      )
    }
  ]

  const ExpandableTable = ({ data }) => (
    <div className="expandable-content p-2">
      <Row>
        <Col md={6}>
          <p><strong>Reserved Date: </strong>{moment(data.reservation.reservedDate).format("YYYY MMMM Do - h:mm a")}</p>
          <p><strong>Max Count: </strong>{data.reservation.maxCount}</p>
        </Col>
        <Col md={6}>
          <p><strong>Customer Note: </strong>
            <Badge color={data.reservation.status === "ACTIVE" ? "light-success" : "light-danger"} pill>
              {data.reservation.status}
            </Badge>
          </p>
          <p><strong>Customer Note:</strong>{data.reservation.customerNote}</p>
        </Col>
      </Row>
    </div>
  )

  useEffect(() => {
    fetchAllReservations()
  }, [])

  const fetchAllReservations = () => {
    getAllReservationFromTableType().then(response => {
      const data = response.data.map((item, index) => ({
        ...item,
        uniqueKey: `${item.reservation.id}-${index}`
      }))
      setData(data)
      setFilteredData(data)
    })
  }

  const handleRespond = async id => {
    setSelectedReservationId(id)
    await fetchQueries(id)
    toggleModal()
  }

  const fetchQueries = async id => {
      await getReservationQueriesById(id).then(response => {
        setQueries(response.data || [])
      }).catch(error => {
        console.error("Error fetching queries:", error)
        setQueries([])
      })
  }

  const handleApprove = id => {
    console.log("Approve button clicked for reservation ID:", id)
  }

  const handleReplySubmit = () => {
    if (!replyMessage.trim()) return

    const userDetails = {
      tableReservationId: selectedReservationId,
      userRole: userData.userRole,
      queryType: 'TABLE',
      message: replyMessage,
      userId: userData.id
    }

    saveQueryForReservation(userDetails)
      .then(async response => {
        if (response.success) {
          await fetchQueries(selectedReservationId)
          setReplyMessage("")
        } else {
          console.error("Error submitting reply:", response.message)
        }
      })
      .catch(error => {
        console.error("Error submitting reply:", error)
      })
  }

  const toggleModal = () => {
    setModal(!modal)
  }

  const handlePagination = page => {
    setCurrentPage(page.selected)
  }

  const handleFilter = e => {
    const value = e.target.value.toLowerCase()
    setSearchValue(value)

    if (value.length) {
      const updatedData = data.filter(item => {
        const codeMatch = item.reservation.reservationCode && item.reservation.reservationCode.toLowerCase().includes(value)
        const statusMatch = item.reservation.status && item.reservation.status.toLowerCase().includes(value)
        const typeMatch = item.reservation.tableReservationType && item.reservation.tableReservationType.toLowerCase().includes(value)

        return codeMatch || statusMatch || typeMatch
      })
      setFilteredData(updatedData)
    } else {
      setFilteredData(data)
    }
  }

  const Previous = () => (
    <span className="align-middle d-none d-md-inline-block">Prev</span>
  )

  const Next = () => (
    <span className="align-middle d-none d-md-inline-block">Next</span>
  )

  const CustomPagination = () => (
    <ReactPaginate
      previousLabel={<Previous />}
      nextLabel={<Next />}
      forcePage={currentPage}
      onPageChange={handlePagination}
      pageCount={Math.ceil((searchValue.length ? filteredData.length : data.length) / 7)}
      breakLabel={"..."}
      pageRangeDisplayed={2}
      marginPagesDisplayed={2}
      activeClassName={"active"}
      pageClassName={"page-item"}
      nextLinkClassName={"page-link"}
      nextClassName={"page-item next"}
      previousClassName={"page-item prev"}
      previousLinkClassName={"page-link"}
      pageLinkClassName={"page-link"}
      breakClassName="page-item"
      breakLinkClassName="page-link"
      containerClassName={"pagination react-paginate pagination-sm justify-content-end pe-1 mt-1"}
    />
  )

  const queryModal = (
    <Modal scrollable isOpen={modal} toggle={toggleModal} size="md">
      <ModalHeader toggle={toggleModal}>Respond to Queries</ModalHeader>
      <ModalBody>
        <div className="chat-container">
          {queries.length !== 0 ? (
            queries.map(query => {
              let avatar, name

              if (query.userRole === 'ADMIN' && query.admin) {
                avatar = query.admin.img || Assets.avater || "default-admin-img.png"
                name = query.admin.name || "Admin"
              } else if (query.userRole === USER_ROLES[1] && query.staff) {
                avatar = query.staff.img || Assets.avater || "default-staff-img.png"
                name = query.staff.name || "Staff"
              } else if (query.userRole === USER_ROLES[0] && query.user) {
                avatar = query.user.img || Assets.avater || "default-customer-img.png"
                name = query.user.name || "Customer"
              } else {
                avatar = "default-avatar.png"
                name = "Unknown"
              }

              return (
                <div key={query.id} className={`chat-message ${query.userRole.toLowerCase()}`}>
                  <div className="message-info">
                    <img src={avatar} alt={`${name} Avatar`} className="avatar" />
                    <div className="message-details" style={{ minWidth: "180px" }}>
                      <div className="message-header">
                        <small color="info" className="message-role pe-2">{query.userRole} | <small
                          className="message-name text-end">{name.split(" ")[0]}</small>
                        </small>
                      </div>
                      <p className="message-text pb-1">{query.message}</p>
                      <small className=" text-dark text-end">{moment(query.createdDate).format("MMM D, YYYY h:mm A")}</small>
                    </div>
                  </div>
                </div>
              )
            })
          ) : (
            <div className={'text-center'}>Not found chat yet!</div>
          )}
        </div>
        <div className="reply-section p-1">
          <Input
            type="textarea"
            value={replyMessage}
            onChange={e => setReplyMessage(e.target.value)}
            placeholder="Type your reply..."
            className="reply-input"
          />
          <Button color="primary" onClick={handleReplySubmit} className="reply-button">Send</Button>
        </div>
      </ModalBody>
    </Modal>
  )

  return (
    <div className="reservation-container">
      <Card>
        <CardHeader className='border-bottom'>
          <CardTitle tag='h4'>Manage Reservations</CardTitle>
        </CardHeader>
        <Row className='justify-content-start mx-0'>
          <Col className='d-flex align-items-center justify-content-end mt-1' md='4' sm='12'>
            <Label className='me-1' for='search-input-1'>
              {t('Search')}
            </Label>
            <Input
              className='dataTable-filter mb-50'
              type='text'
              bsSize='sm'
              id='search-input-1'
              value={searchValue}
              onChange={handleFilter}
            />
          </Col>
        </Row>
        <div className='react-dataTable'>
          <DataTable
            columns={columns}
            data={filteredData}
            expandableRows
            expandableRowsComponent={ExpandableTable}
            pagination
            paginationComponent={CustomPagination}
            // noDataComponent="No data found"
            keyField="uniqueKey"
          />
        </div>
      </Card>

      {queryModal}
    </div>
  )
}

export default Reservation
