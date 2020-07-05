export const environment = {
  production: false,
  apiViaCep: 'https://viacep.com.br/ws/',
  apiUrl: 'http://localhost:8080/api',
  clientId : 'massariolapiangular',
  secretId : 'm@ss@r1@l@p1@ngul@r',
  tokenName: 'massariolToken',
  tokenWhitelistedDomains: ['localhost:8080'],
  tokenBlacklistedRoutes: [new RegExp('\/oauth\/token')]
};
