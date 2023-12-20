import React from 'react';
import './App.css';
import CategoryForm from './components/CategoryComponent/CategoryComponent';
import { AuthProvider } from './auth/AuthContext';
import VenueForm from './components/VenueComponent/CreateVenueComponent';
import OrganizationForm from './components/OrganizationComponent/OrganizationForm';
import EventForm from './components/EventComponent/EventForm';
import TicketForm from './components/TicketComponent/TicketForm';
import SignUp from './components/RegisterComponent/RegisterComponent';
import RegisterComponent from './components/RegisterComponent/RegisterComponent';
import LoginComponent from './components/LoginComponent/LoginComponent';
import CreateVenueComponent from './components/VenueComponent/CreateVenueComponent';
// function App() {
//   return (
//     <div className="App">
//       <AuthProvider>
//         <RegisterForm />
//         <LoginForm />
//         <CategoryForm />
//         <VenueForm />
//         <OrganizationForm />
//         <EventForm />
//         <TicketForm />
//       </AuthProvider>
//     </div>
//   );
// }
function App() {
return (<div className="App">
<AuthProvider>
  <RegisterComponent></RegisterComponent>
  <LoginComponent></LoginComponent>
  <CreateVenueComponent></CreateVenueComponent>
</AuthProvider>
</div>)}



export default App;
