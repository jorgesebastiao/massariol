export const environment = {
  production: true,
  apiViaCep: 'https://viacep.com.br/ws/',
  apiUrl: 'http://certificado.massarioltreinamentos.com.br/api',
  clientId: 'massariolapiangular',
  secretId: 'm@ss@r1@l@p1@ngul@r',
  tokenName: 'massariolToken',
  tokenWhitelistedDomains: ['certificado.massarioltreinamentos.com.br'],
  tokenBlacklistedRoutes: [new RegExp('\/oauth\/token')]
};
