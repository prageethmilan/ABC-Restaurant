// ** React Imports
import { Link, useNavigate } from "react-router-dom";
import { useEffect, useState } from 'react'

// ** Custom Components
import Avatar from '@components/avatar'

// ** Utils
import { isUserLoggedIn } from '@utils'

// ** Store & Actions
import { useDispatch } from 'react-redux'


// ** Third Party Components
import { User, Mail, CheckSquare, MessageSquare, Settings, CreditCard, HelpCircle, Power } from 'react-feather'

// ** Reactstrap Imports
import { UncontrolledDropdown, DropdownMenu, DropdownToggle, DropdownItem } from 'reactstrap'

// ** Default Avatar Image
import defaultAvatar from '@src/assets/images/portrait/small/avatar-s-11.jpg'
import { USER_OBJECT } from "@src/router/RouteConstant"
import { removeLocalStorageValues } from "@src/utility/commonFun"
import { HOME_PATH } from "@src/router/routes/route-constant";

const UserDropdown = () => {
  // ** Store Vars
  const dispatch = useDispatch()
  const navigate = useNavigate()

  // ** State
  const [userData, setUserData] = useState(null)

  //** ComponentDidMount
  useEffect(() => {
    if (isUserLoggedIn() !== null) {
      setUserData(JSON.parse(localStorage.getItem("USER_OBJECT")))
    }
  }, [])

  //** Vars
  const userAvatar = (userData && userData.img) || defaultAvatar

  const handleLogout = () => {
    removeLocalStorageValues()
    navigate(HOME_PATH)
  }
  return (
    <UncontrolledDropdown tag='li' className='dropdown-user nav-item'>
      <DropdownToggle href='/' tag='a' className='nav-link dropdown-user-link' onClick={e => e.preventDefault()}>
        <div className="user-nav d-sm-flex d-none">
        <span className="user-name fw-bold ">
          {userData && userData.name ? userData.name.split(" ")[0] : "John"}
        </span>
          <span className="user-status">{(userData && userData?.userRole) || "Admin"}</span>
        </div>
        <Avatar img={userAvatar} imgHeight="40" imgWidth="40" status="online" />
      </DropdownToggle>
      <DropdownMenu end>
        <DropdownItem tag={Link} to='/my-profile'>
          <User size={14} className='me-75' />
          <span className='align-middle'>Profile</span>
        </DropdownItem>
        {/*<DropdownItem tag={Link} to='/apps/email'>*/}
        {/*  <Mail size={14} className='me-75' />*/}
        {/*  <span className='align-middle'>Inbox</span>*/}
        {/*</DropdownItem>*/}
        {/*<DropdownItem tag={Link} to='/apps/todo'>*/}
        {/*  <CheckSquare size={14} className='me-75' />*/}
        {/*  <span className='align-middle'>Tasks</span>*/}
        {/*</DropdownItem>*/}
        {/*<DropdownItem tag={Link} to='/apps/chat'>*/}
        {/*  <MessageSquare size={14} className='me-75' />*/}
        {/*  <span className='align-middle'>Chats</span>*/}
        {/*</DropdownItem>*/}
        {/*<DropdownItem divider />*/}

        <DropdownItem style={{ width: "100%" }} onClick={handleLogout}>
          <Power size={14} className="me-75" />
          <span className="align-middle">Logout</span>
        </DropdownItem>
      </DropdownMenu>
    </UncontrolledDropdown>
  )
}

export default UserDropdown
