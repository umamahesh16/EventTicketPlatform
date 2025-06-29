// API Endpoints
export const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || 'http://localhost:8081';
export const API_ENDPOINTS = {
  // User Service
  AUTH: {
    LOGIN: '/api/auth/login',
    REGISTER: '/api/auth/register',
    REFRESH: '/api/auth/refresh',
    LOGOUT: '/api/auth/logout',
    PROFILE: '/api/auth/profile',
  },
  USERS: {
    BASE: '/api/users',
    PROFILE: '/api/users/profile',
    UPDATE_PROFILE: '/api/users/profile',
    CHANGE_PASSWORD: '/api/users/change-password',
  },
  // Event Service
  EVENTS: {
    BASE: '/api/events',
    FEATURED: '/api/events/featured',
    SEARCH: '/api/events/search',
    CATEGORIES: '/api/events/categories',
    VENUES: '/api/events/venues',
    ORGANIZERS: '/api/events/organizers',
  },
  // Ticket Service
  TICKETS: {
    BASE: '/api/tickets',
    PURCHASE: '/api/tickets/purchase',
    USER_TICKETS: '/api/tickets/user',
    VALIDATE: '/api/tickets/validate',
    CANCEL: '/api/tickets/cancel',
  },
} as const;

// Application Routes
export const ROUTES = {
  HOME: '/',
  EVENTS: '/events',
  EVENT_DETAILS: '/events/:id',
  CREATE_EVENT: '/events/create',
  EDIT_EVENT: '/events/:id/edit',
  LOGIN: '/login',
  REGISTER: '/register',
  PROFILE: '/profile',
  DASHBOARD: '/dashboard',
  MY_TICKETS: '/my-tickets',
  TICKET_DETAILS: '/tickets/:id',
  ADMIN: '/admin',
  NOT_FOUND: '/404',
} as const;

// Navigation Items
export const NAV_ITEMS = [
  {
    label: 'Home',
    href: ROUTES.HOME,
  },
  {
    label: 'Events',
    href: ROUTES.EVENTS,
  },
  {
    label: 'My Tickets',
    href: ROUTES.MY_TICKETS,
    requiresAuth: true,
  },
  {
    label: 'Dashboard',
    href: ROUTES.DASHBOARD,
    requiresAuth: true,
    roles: ['ADMIN', 'ORGANIZER'],
  },
] as const;

// Event Categories
export const EVENT_CATEGORIES = [
  { value: 'CONCERT', label: 'Concert', icon: '🎵' },
  { value: 'CONFERENCE', label: 'Conference', icon: '🎤' },
  { value: 'SPORTS', label: 'Sports', icon: '⚽' },
  { value: 'THEATER', label: 'Theater', icon: '🎭' },
  { value: 'FESTIVAL', label: 'Festival', icon: '🎪' },
  { value: 'WORKSHOP', label: 'Workshop', icon: '🔧' },
  { value: 'EXHIBITION', label: 'Exhibition', icon: '🖼️' },
  { value: 'OTHER', label: 'Other', icon: '📅' },
] as const;

// Event Status
export const EVENT_STATUS = [
  { value: 'DRAFT', label: 'Draft', color: 'gray' },
  { value: 'PUBLISHED', label: 'Published', color: 'green' },
  { value: 'CANCELLED', label: 'Cancelled', color: 'red' },
  { value: 'COMPLETED', label: 'Completed', color: 'blue' },
] as const;

// Order Status
export const ORDER_STATUS = [
  { value: 'PENDING', label: 'Pending', color: 'yellow' },
  { value: 'CONFIRMED', label: 'Confirmed', color: 'green' },
  { value: 'CANCELLED', label: 'Cancelled', color: 'red' },
  { value: 'REFUNDED', label: 'Refunded', color: 'purple' },
  { value: 'EXPIRED', label: 'Expired', color: 'gray' },
] as const;

// User Roles
export const USER_ROLES = [
  { value: 'USER', label: 'User', color: 'blue' },
  { value: 'ADMIN', label: 'Admin', color: 'red' },
  { value: 'ORGANIZER', label: 'Organizer', color: 'green' },
] as const;

// Form Validation Rules
export const VALIDATION_RULES = {
  EMAIL: {
    required: 'Email is required',
    pattern: {
      value: /^[^\s@]+@[^\s@]+\.[^\s@]+$/,
      message: 'Please enter a valid email address',
    },
  },
  PASSWORD: {
    required: 'Password is required',
    minLength: {
      value: 8,
      message: 'Password must be at least 8 characters long',
    },
    pattern: {
      value: /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d@$!%*?&]{8,}$/,
      message: 'Password must contain at least one uppercase letter, one lowercase letter, and one number',
    },
  },
  PHONE: {
    pattern: {
      value: /^\+?[\d\s\-\(\)]{10,}$/,
      message: 'Please enter a valid phone number',
    },
  },
  REQUIRED: (fieldName: string) => ({
    required: `${fieldName} is required`,
  }),
} as const;

// File Upload Configuration
export const FILE_UPLOAD_CONFIG = {
  MAX_FILE_SIZE: 5 * 1024 * 1024, // 5MB
  ALLOWED_IMAGE_TYPES: ['image/jpeg', 'image/png', 'image/webp', 'image/gif'],
  MAX_IMAGES_PER_EVENT: 10,
} as const;

// Pagination Configuration
export const PAGINATION_CONFIG = {
  DEFAULT_PAGE_SIZE: 12,
  PAGE_SIZE_OPTIONS: [6, 12, 24, 48],
  MAX_PAGE_SIZE: 100,
} as const;

// Local Storage Keys
export const STORAGE_KEYS = {
  AUTH_TOKEN: 'auth_token',
  REFRESH_TOKEN: 'refresh_token',
  USER_PROFILE: 'user_profile',
  THEME: 'theme',
  LANGUAGE: 'language',
  CART: 'cart',
} as const;

// Theme Configuration
export const THEME_CONFIG = {
  LIGHT: 'light',
  DARK: 'dark',
  SYSTEM: 'system',
} as const;

// Language Configuration
export const LANGUAGE_CONFIG = {
  EN: 'en',
  ES: 'es',
  FR: 'fr',
} as const;

// Error Messages
export const ERROR_MESSAGES = {
  NETWORK_ERROR: 'Network error. Please check your connection and try again.',
  UNAUTHORIZED: 'You are not authorized to perform this action.',
  FORBIDDEN: 'Access denied. You do not have permission to view this resource.',
  NOT_FOUND: 'The requested resource was not found.',
  VALIDATION_ERROR: 'Please check your input and try again.',
  SERVER_ERROR: 'An unexpected error occurred. Please try again later.',
  TIMEOUT_ERROR: 'Request timed out. Please try again.',
} as const;

// Success Messages
export const SUCCESS_MESSAGES = {
  LOGIN_SUCCESS: 'Successfully logged in!',
  REGISTER_SUCCESS: 'Account created successfully!',
  LOGOUT_SUCCESS: 'Successfully logged out!',
  PROFILE_UPDATED: 'Profile updated successfully!',
  PASSWORD_CHANGED: 'Password changed successfully!',
  EVENT_CREATED: 'Event created successfully!',
  EVENT_UPDATED: 'Event updated successfully!',
  EVENT_DELETED: 'Event deleted successfully!',
  TICKET_PURCHASED: 'Ticket purchased successfully!',
  TICKET_CANCELLED: 'Ticket cancelled successfully!',
} as const;

// Animation Durations
export const ANIMATION_DURATIONS = {
  FAST: 150,
  NORMAL: 300,
  SLOW: 500,
} as const;

// Breakpoints (matching Tailwind CSS)
export const BREAKPOINTS = {
  SM: 640,
  MD: 768,
  LG: 1024,
  XL: 1280,
  '2XL': 1536,
} as const;

// Date Formats
export const DATE_FORMATS = {
  DISPLAY: 'MMM dd, yyyy',
  DISPLAY_TIME: 'MMM dd, yyyy HH:mm',
  INPUT: 'yyyy-MM-dd',
  INPUT_TIME: 'yyyy-MM-ddTHH:mm',
  TIME: 'HH:mm',
} as const; 