import React, { useEffect, useState } from 'react';
import './App.css';
import CategoryForm from './components/CategoryComponent/CategoryComponent';
import { useAuth } from './auth/AuthContext';
import RegisterComponent from './components/RegisterComponent/RegisterComponent';
import LoginComponent from './components/LoginComponent/LoginComponent';
import CreateVenueComponent from './components/VenueComponent/CreateVenueComponent';
import CreateOrganizationComponent from './components/OrganizationComponent/CreateOrganizationComponent';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import CreateEventComponent from './components/EventComponent/CreateEventComponent';
import SelectVenueComponent from './components/VenueComponent/selectVenueComponent';
function App() {
  const { token, setToken, userId, setUser } = useAuth();
  const [isLogged,setIsLogged] = useState(false);
  useEffect(() => {
    console.log(token,userId);
    if (token && userId) {
      setIsLogged(true);
      console.log(isLogged);
    }
    else {
      setIsLogged(false);
      console.log(isLogged);
    }
}, [token, userId]);
useEffect(() => {
  if (isLogged) {
    console.log("User is logged in");
    // Perform any action that needs to happen when the user is logged in
  } else {
    console.log("User is not logged in");
    // Perform any action that needs to happen when the user is not logged in
  }
}, [isLogged]);
return (<div className="App">
  <LocalizationProvider dateAdapter={AdapterDayjs}>

  <RegisterComponent></RegisterComponent>
  {isLogged ? <CreateEventComponent></CreateEventComponent>:null}
  <LoginComponent></LoginComponent>
  <CreateVenueComponent></CreateVenueComponent>
  <CreateOrganizationComponent></CreateOrganizationComponent>

</LocalizationProvider>
</div>)}



export default App;
