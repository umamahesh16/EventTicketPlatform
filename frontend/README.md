# Event Ticket Platform - Frontend

A modern, responsive React TypeScript frontend for the Event Ticket Platform, built with Vite, Tailwind CSS, and following best practices for maintainability and scalability.

## 🚀 Features

- **Modern Tech Stack**: React 18, TypeScript, Vite, Tailwind CSS
- **State Management**: Zustand for global state, React Query for server state
- **Routing**: React Router v6 with protected routes
- **Form Handling**: React Hook Form with validation
- **UI Components**: Custom component library with Tailwind CSS
- **Authentication**: JWT-based auth with refresh tokens
- **Error Handling**: Comprehensive error boundaries and toast notifications
- **Performance**: Code splitting, lazy loading, and optimized builds
- **Testing**: Vitest with React Testing Library
- **Code Quality**: ESLint, Prettier, and TypeScript strict mode

## 🏗️ Architecture

```
src/
├── components/          # Reusable UI components
│   ├── ui/             # Base UI components (Button, Input, etc.)
│   ├── layout/         # Layout components (Header, Footer, etc.)
│   └── forms/          # Form-specific components
├── pages/              # Page components
├── hooks/              # Custom React hooks
├── services/           # API services and external integrations
├── store/              # Zustand stores
├── contexts/           # React contexts
├── types/              # TypeScript type definitions
├── utils/              # Utility functions
├── constants/          # Application constants
└── assets/             # Static assets (images, icons, etc.)
```

## 🛠️ Tech Stack

### Core
- **React 18** - UI library
- **TypeScript** - Type safety
- **Vite** - Build tool and dev server
- **React Router v6** - Client-side routing

### Styling
- **Tailwind CSS** - Utility-first CSS framework
- **Tailwind CSS Plugins** - Forms, Typography, Aspect Ratio
- **Framer Motion** - Animation library

### State Management
- **Zustand** - Lightweight state management
- **React Query** - Server state management
- **React Hook Form** - Form state management

### HTTP Client
- **Axios** - HTTP client with interceptors

### Development Tools
- **ESLint** - Code linting
- **Prettier** - Code formatting
- **Husky** - Git hooks
- **Vitest** - Unit testing
- **React Testing Library** - Component testing

## 📦 Installation

### Prerequisites

- Node.js 18+ 
- npm 9+ or yarn 1.22+
- Backend services running (see backend README)

### Setup

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd EventTicketPlatform/frontend
   ```

2. **Install dependencies**
   ```bash
   npm install
   # or
   yarn install
   ```

3. **Environment configuration**
   ```bash
   cp env.example .env.local
   # Edit .env.local with your configuration
   ```

4. **Start development server**
   ```bash
   npm run dev
   # or
   yarn dev
   ```

5. **Open your browser**
   Navigate to `http://localhost:3000`

## 🔧 Development

### Available Scripts

```bash
# Development
npm run dev          # Start development server
npm run build        # Build for production
npm run preview      # Preview production build

# Code Quality
npm run lint         # Run ESLint
npm run lint:fix     # Fix ESLint errors
npm run format       # Format code with Prettier
npm run format:check # Check code formatting
npm run type-check   # Run TypeScript type checking

# Testing
npm run test         # Run tests
npm run test:ui      # Run tests with UI
npm run test:coverage # Run tests with coverage

# Git Hooks
npm run prepare      # Install Husky hooks
```

### Development Guidelines

#### Code Style
- Follow TypeScript strict mode
- Use functional components with hooks
- Implement proper error boundaries
- Write meaningful component and function names
- Add JSDoc comments for complex functions

#### Component Structure
```typescript
// ComponentName.tsx
import React from 'react';
import { ComponentNameProps } from './types';

export const ComponentName: React.FC<ComponentNameProps> = ({ prop1, prop2 }) => {
  // Component logic here
  
  return (
    <div>
      {/* JSX here */}
    </div>
  );
};

export default ComponentName;
```

#### File Naming
- Components: `PascalCase.tsx`
- Hooks: `useCamelCase.ts`
- Utilities: `camelCase.ts`
- Types: `camelCase.types.ts`
- Constants: `UPPER_SNAKE_CASE.ts`

#### State Management
- Use Zustand for global state
- Use React Query for server state
- Use React Hook Form for form state
- Use local state for component-specific state

#### API Integration
- Use the `apiService` for HTTP requests
- Implement proper error handling
- Use React Query for caching and synchronization
- Handle loading and error states

## 🧪 Testing

### Running Tests
```bash
# Run all tests
npm run test

# Run tests in watch mode
npm run test -- --watch

# Run tests with coverage
npm run test:coverage

# Run tests with UI
npm run test:ui
```

### Test Structure
```
src/
├── __tests__/        # Test files
├── components/       # Component tests alongside components
└── test/            # Test utilities and setup
```

### Testing Guidelines
- Write tests for all components
- Test user interactions and edge cases
- Mock external dependencies
- Use meaningful test descriptions
- Follow AAA pattern (Arrange, Act, Assert)

## 🏗️ Build & Deployment

### Production Build
```bash
npm run build
```

The build output will be in the `dist/` directory.

### Environment Variables
Create a `.env.local` file for local development:
```env
VITE_API_BASE_URL=http://localhost:8081
VITE_EVENT_SERVICE_URL=http://localhost:8082
VITE_TICKET_SERVICE_URL=http://localhost:8083
```

### Docker Deployment
```bash
# Build Docker image
docker build -t event-ticket-frontend .

# Run container
docker run -p 3000:3000 event-ticket-frontend
```

## 📁 Project Structure

### Components
- **UI Components**: Reusable, styled components
- **Layout Components**: Page layout and navigation
- **Form Components**: Form-specific components
- **Feature Components**: Business logic components

### Pages
- **Public Pages**: Home, Events, Login, Register
- **Protected Pages**: Profile, Dashboard, My Tickets
- **Admin Pages**: Event Management, User Management

### Services
- **API Service**: HTTP client configuration
- **Auth Service**: Authentication and authorization
- **Event Service**: Event-related API calls
- **Ticket Service**: Ticket-related API calls

### Hooks
- **Custom Hooks**: Reusable logic
- **API Hooks**: React Query hooks for API calls
- **Form Hooks**: Form-specific hooks

### Store
- **Auth Store**: Authentication state
- **UI Store**: UI state (theme, modals, etc.)
- **Cart Store**: Shopping cart state

## 🔒 Security

### Authentication
- JWT-based authentication
- Automatic token refresh
- Protected routes
- Role-based access control

### Data Validation
- Client-side validation with React Hook Form
- Server-side validation
- Input sanitization
- XSS protection

### HTTPS
- Secure API communication
- Secure cookie handling
- CSP headers

## 🚀 Performance

### Optimization Techniques
- Code splitting with React.lazy()
- Image optimization
- Bundle analysis
- Tree shaking
- Memoization

### Monitoring
- Performance metrics
- Error tracking
- User analytics
- Bundle size monitoring

## 🤝 Contributing

### Development Workflow
1. Create a feature branch
2. Make your changes
3. Write/update tests
4. Run linting and formatting
5. Submit a pull request

### Code Review Checklist
- [ ] Code follows style guidelines
- [ ] Tests are written and passing
- [ ] No console.log statements
- [ ] Proper error handling
- [ ] Accessibility considerations
- [ ] Performance considerations

## 📚 Resources

### Documentation
- [React Documentation](https://react.dev/)
- [TypeScript Handbook](https://www.typescriptlang.org/docs/)
- [Vite Documentation](https://vitejs.dev/)
- [Tailwind CSS Documentation](https://tailwindcss.com/docs)
- [React Query Documentation](https://tanstack.com/query/latest)

### Tools
- [React Developer Tools](https://chrome.google.com/webstore/detail/react-developer-tools/fmkadmapgofadopljbjfkapdkoienihi)
- [TypeScript Playground](https://www.typescriptlang.org/play)
- [Tailwind CSS Playground](https://play.tailwindcss.com/)

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](../LICENSE) file for details.

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review existing issues and discussions

---

**Happy Coding! 🎉** 