import { ReactNode } from 'react';

interface ProtectedRouteProps {
  children: ReactNode;
  roles?: string[];
}

const ProtectedRoute = ({ children, roles }: ProtectedRouteProps) => {
  // TODO: Implement actual authentication logic
  const isAuthenticated = false; // This should come from your auth context
  const userRole = 'USER'; // This should come from your auth context

  if (!isAuthenticated) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50 dark:bg-gray-900">
        <div className="max-w-md w-full bg-white dark:bg-gray-800 shadow-lg rounded-lg p-6">
          <div className="text-center">
            <h3 className="text-lg font-medium text-gray-900 dark:text-white">
              Authentication Required
            </h3>
            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              Please log in to access this page.
            </p>
            <div className="mt-6">
              <a
                href="/login"
                className="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700"
              >
                Go to Login
              </a>
            </div>
          </div>
        </div>
      </div>
    );
  }

  if (roles && !roles.includes(userRole)) {
    return (
      <div className="min-h-screen flex items-center justify-center bg-gray-50 dark:bg-gray-900">
        <div className="max-w-md w-full bg-white dark:bg-gray-800 shadow-lg rounded-lg p-6">
          <div className="text-center">
            <h3 className="text-lg font-medium text-gray-900 dark:text-white">
              Access Denied
            </h3>
            <p className="mt-2 text-sm text-gray-500 dark:text-gray-400">
              You don't have permission to access this page.
            </p>
            <div className="mt-6">
              <a
                href="/"
                className="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-primary-600 hover:bg-primary-700"
              >
                Go Home
              </a>
            </div>
          </div>
        </div>
      </div>
    );
  }

  return <>{children}</>;
};

export default ProtectedRoute; 