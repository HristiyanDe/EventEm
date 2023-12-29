import React, { useEffect, useState } from 'react';
import { API_EVENTS_PATH } from '../../constants/apiConstants';
import axios from 'axios';
import { useAuth } from '../../auth/AuthContext';
import { EventRequest } from '../../models/EventRequest';
import { EventStatusEnum } from '../../models/enums/EventStatusEnum';
import Avatar from '@mui/material/Avatar';
import Button from '@mui/material/Button';
import Select from '@mui/material/Select';
import CssBaseline from '@mui/material/CssBaseline';
import TextField from '@mui/material/TextField';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';
import Box from '@mui/material/Box';
import LockOutlinedIcon from '@mui/icons-material/LockOutlined';
import Typography from '@mui/material/Typography';
import Container from '@mui/material/Container';
import { ThemeProvider } from '@mui/material/styles';
import { defaultTheme } from '../RegisterComponent/RegisterComponent';
import { SelectChangeEvent } from '@mui/material/Select';
import { DatePicker } from '@mui/x-date-pickers';
import MenuItem from '@mui/material/MenuItem';
import { CategoryRequest } from '../../models/CategoryRequest';
import {categoryService} from '../../api/categoryService';
const CreateEventComponent: React.FC = () => {
    const { token, setToken } = useAuth();
const [formData, setFormData] = useState<EventRequest>({
name: '',
eventStatus: EventStatusEnum.NONE,
startDate: '',
endDate: '',
venueId: 0,
organizationId: 0,
description: '',
maxAttendees: 0,
categories: [{
    categoryName: '',
}],
//TODO: make the user able to add multiple categories to an event
//TODO: Refactor handle functions to be more generic
});
const [categories, setCategories] = useState<CategoryRequest[]>([]);
useEffect(() => {
    const fetchCategories = async () => {
        const categories = await categoryService.getCategories();
        setCategories(categories);
        console.log(categories);
    };
    fetchCategories();
}, []);
  
const handleSubmit = async (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();
    try {
        console.log(token);
        const response = await axios.post(API_EVENTS_PATH, formData, {
            headers: {
                Authorization: `Bearer ${token}`,
            },
        });
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};
const handleSelect = (event: SelectChangeEvent<EventStatusEnum>) => {
  setFormData({ ...formData, eventStatus: event.target.value as EventStatusEnum });
};
const handleMultipleSelect = (event: SelectChangeEvent<CategoryRequest[]>) => {
  setFormData({ ...formData, categories: event.target.value as CategoryRequest[] });
};
const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
  
    if (name.startsWith("categoryName")) {
      const categoryIndex = Number(name.split("_")[1]);
      const updatedCategories = [...formData.categories];
      updatedCategories[categoryIndex] = { categoryName: value };
      setFormData((prevFormData) => ({
        ...prevFormData,
        categories: updatedCategories,
      }));
    } else {
      setFormData((prevFormData) => ({
        ...prevFormData,
        [name]: value,
      }));
    }
  };
  const handleDateChange = (field: string, date: Date | null) => {
    if (date) {
        setFormData({
            ...formData,
            [field]: date.toISOString(),
        });
    }
};
  return (
    <ThemeProvider theme={defaultTheme}>
      <Container component="main" maxWidth="xs">
        <CssBaseline />
        <Box
          sx={{
            marginTop: 8,
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
          }}
        >
          <Avatar sx={{ m: 1, bgcolor: 'secondary.main' }}>
            <LockOutlinedIcon />
          </Avatar>
          <Typography component="h1" variant="h5">
            Create Event
          </Typography>
          <Box component="form" noValidate onSubmit={handleSubmit} sx={{ mt: 3 }}>
            <Grid container spacing={2}>
            <Grid item xs={12} sm={6}>
                <TextField
                  autoComplete="name"
                  name="name"
                  required
                  fullWidth
                  id="create-event-name"
                  label="Event name"
                  autoFocus
                  value = {formData.name}
                  onChange={handleInputChange}
                />
              </Grid>
              <Grid item xs={12}>
                <Select
                  required
                  fullWidth
                  name="eventStatus"
                  label="Event Status"
                  id="create-event-status"
                  value = {formData.eventStatus}
                  onChange={handleSelect}

                >
                <MenuItem value={EventStatusEnum.NONE}>None</MenuItem>
                <MenuItem value={EventStatusEnum.DELAYED}>Delayed</MenuItem>
                <MenuItem value={EventStatusEnum.CANCELLED}>Cancelled</MenuItem>
                <MenuItem value={EventStatusEnum.FINISHED}>Active</MenuItem>
                </Select>
              </Grid>
              <Grid item xs={12} sm={6}>
                <DatePicker
                  name="startDate"
                  label="Start Date"
                  autoFocus
                  value = {formData.startDate}
                  onChange={(date) => handleDateChange('startDate', date ? new Date(date) : null)}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <DatePicker
                  label="End Date"
                  name="create-event-end-date"
                  value = {formData.endDate}
                  onChange={(date) => handleDateChange('endDate', date ? new Date(date) : null)}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id="create-eventvenue-id"
                  label="Venue ID"
                  name="venueId"
                  value = {formData.venueId}
                  onChange={handleInputChange}
                />
               </Grid>
               <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id="create-event-organization-id"
                  label="Organization ID"
                  name="organizationId"
                  value = {formData.organizationId}
                  onChange={handleInputChange}
                />
              </Grid>
              <Grid item xs={12} sm={6}>
                <TextField
                  required
                  fullWidth
                  id="create-event-description"
                  label="Event Description"
                  name="description"
                  value = {formData.description}
                  onChange={handleInputChange}
                />
              </Grid>
              <Grid item xs={12}>
                <TextField
                  required
                  fullWidth
                  id="create-event-max-attendees"
                  label="Max Attendees"
                  name="maxAttendees"
                  value = {formData.maxAttendees}
                  onChange={handleInputChange}
                />
              </Grid>
              <Grid item xs={12}>
                <Select
                  required
                  fullWidth
                  multiple
                  name="eventCategories"
                  label="Event Categories"
                  id="create-event-categories"
                  value={formData.categories}
                  onChange={handleMultipleSelect}

                >
                {categories.map((category) => (
                    <MenuItem key = {category.categoryName} value={category.categoryName}>
                        {category.categoryName}
                    </MenuItem>
                ))}
                </Select>
              </Grid>
            </Grid>
            <Button
              type="submit"
              fullWidth
              variant="contained"
              sx={{ mt: 3, mb: 2 }}
            >
              Create Event
            </Button>
          </Box>
        </Box>
      </Container>
    </ThemeProvider>
  );
        }
        export default CreateEventComponent;