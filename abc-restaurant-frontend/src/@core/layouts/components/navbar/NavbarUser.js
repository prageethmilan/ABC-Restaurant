// ** Dropdowns Imports
import UserDropdown from "./UserDropdown"
import CartDropdown from "@layouts/components/navbar/CartDropdown"
import React, { useEffect, useState } from "react";
import { IS_LOGIN } from "@src/router/RouteConstant";
import { USER_ROLES } from "@src/const/const";

const NavbarUser = () => {
  const [userStatus, setUserStatus] = useState(localStorage.getItem(IS_LOGIN))

  useEffect(() => {
    setUserStatus(localStorage.getItem(IS_LOGIN))
  }, [userStatus])

  return (<ul className="nav navbar-nav align-items-center ms-auto">
    <div className={userStatus === USER_ROLES[2] || userStatus === USER_ROLES[1] ? 'd-none' : ''}>
      <CartDropdown />
    </div>
    <UserDropdown />
  </ul>)
}
export default NavbarUser
