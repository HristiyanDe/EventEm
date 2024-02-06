import React from 'react';
import './App.css';
import CategoryForm from './components/CategoryComponent/CategoryComponent';
import { AuthProvider } from './auth/AuthContext';
import RegisterComponent from './components/RegisterComponent/RegisterComponent';
import LoginComponent from './components/LoginComponent/LoginComponent';
import CreateVenueComponent from './components/VenueComponent/CreateVenueComponent';
import CreateOrganizationComponent from './components/OrganizationComponent/CreateOrganizationComponent';
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import CreateEventComponent from './components/EventComponent/CreateEventComponent';
import SelectVenueComponent from './components/VenueComponent/selectVenueComponent';
function App() {
return (<div className="App">
  <LocalizationProvider dateAdapter={AdapterDayjs}>
<AuthProvider>
  <RegisterComponent></RegisterComponent>
  <CreateEventComponent></CreateEventComponent>
  <LoginComponent></LoginComponent>
  <CreateVenueComponent></CreateVenueComponent>
  <CreateOrganizationComponent></CreateOrganizationComponent>
</AuthProvider>
</LocalizationProvider>
</div>)}



export default App;
