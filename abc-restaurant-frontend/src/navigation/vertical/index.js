
import {
  Grid,
  Users,
  UserCheck,
  Home,
  CheckSquare,
  ShoppingBag,
  Mail,
  DollarSign,
  Clipboard,
  Server, User, MessageSquare
} from "react-feather"
import { USER_ROLES } from "@src/const/const";

export const allRoutes = [
  {
    id: "dashboard",
    title: "Dashboard",
    icon: <Grid size={25} />,
    navLink: "/dashboard",
    roles: [USER_ROLES[2], USER_ROLES[1]]
  },
  {
    id: "users",
    title: "Manage Users",
    icon: <Users size={25} />,
    navLink: "/adminPanel/manageUser",
    roles: [USER_ROLES[2]]
  },
  {
    id: "customers",
    title: "Manage Customers",
    icon: <UserCheck size={25} />,
    navLink: "/adminPanel/customers",
    roles: [USER_ROLES[2]]
  },
  {
    id: "restaurant",
    title: "Restaurants",
    icon: <Home size={25} />,
    navLink: "/adminPanel/manageRestaurant",
    roles: [USER_ROLES[2]]
  },
  {
    id: "facility",
    title: "Facilities",
    icon: <CheckSquare size={25} />,
    navLink: "/adminPanel/manageFacility",
    roles: [USER_ROLES[2]]
  },
  {
    id: "meals",
    title: "Manage Meals",
    icon: <ShoppingBag size={25} />,
    navLink: "/adminPanel/manageMeals",
    roles: [USER_ROLES[2]]
  },
  {
    id: "queries",
    title: "Manage Queries",
    icon: <Mail size={25} />,
    navLink: "/adminPanel/queries",
    roles: [USER_ROLES[2]]
  },
  {
    id: "reservations",
    title: "Reservations",
    icon: <Mail size={25} />,
    navLink: "/adminPanel/reservation",
    roles: [USER_ROLES[2], USER_ROLES[1]]
  },
  {
    id: "orders",
    title: "Manage Orders",
    icon: <Mail size={25} />,
    navLink: "/adminPanel/orders",
    roles: [USER_ROLES[2], USER_ROLES[1]]
  },
  {
    id: "payments",
    title: "Manage Payments",
    icon: <DollarSign size={25} />,
    navLink: "/adminPanel/payments",
    roles: [USER_ROLES[2], USER_ROLES[1]]
  },
  {
    id: "reports",
    title: "Manage Reports",
    icon: <Clipboard size={25} />,
    navLink: "/adminPanel/reports",
    roles: [USER_ROLES[2]]
  },

  {
    id: "my_profile",
    title: "My Profile",
    icon: <User size={25} />,
    navLink: "/my-profile",
    roles: [USER_ROLES[0]]
  },
  {
    id: "my_order",
    title: "My Orders",
    icon: <Clipboard size={25} />,
    navLink: "/my-orders",
    roles: [USER_ROLES[0]]
  },
  {
    id: "my_reservation",
    title: "My Reservations",
    icon: <Server size={25} />,
    navLink: "/reservations",
    roles: [USER_ROLES[0]]
  },
  {
    id: "my_queries",
    title: "My Queries",
    icon: <MessageSquare size={25} />,
    navLink: "/my-queries",
    roles: [USER_ROLES[0]]
  }
]
