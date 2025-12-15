import axios from "axios";

const API = axios.create({
  baseURL: "http://localhost:8081",
});

export default API;
// ➕ NEW METHODS
export const scheduleReminder = (id) =>
  API.post(`/schedule/set/${id}`);

export const reminderStatus = (id) =>
  API.get(`/schedule/reminders/${id}`);