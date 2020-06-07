#!/bin/bash
echo "Iniciando instalação.."
cd massariol
sudo rm -rf var/www/html/**
sudo cp -rf client/dist/** /var/www/html/
sudo service massariol.service stop
sudo rm -rf /opt/massariol-ws/**
sudo cp ws/massariol-distribution.jar /opt/massariol-ws/
sudo service massariol.service start

echo "Instalação realizda com sucesso!"
exit 0