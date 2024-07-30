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
import NavbarComponent from './components/navbar/navbar';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
function App() {
  const { token, setToken, userId, setUser } = useAuth();
return (<div className="App">
  <NavbarComponent></NavbarComponent>
  <LocalizationProvider dateAdapter={AdapterDayjs}>
  <BrowserRouter>
  <Routes>
    <Route path="/register" element={<RegisterComponent />} />
    <Route path="/login" element={<LoginComponent />} />
    <Route path="/create-event" element={<CreateEventComponent />} />
    <Route path="/create-venue" element={<CreateVenueComponent />} />
    <Route path="/create-organization" element={<CreateOrganizationComponent />} />
  </Routes>
</BrowserRouter>
</LocalizationProvider>
</div>)}



export default App;
