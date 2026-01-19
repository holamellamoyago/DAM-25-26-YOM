from odoo import models,fields,api

class aula(models.Model):

    _name="colegio.aula"
    _description="Tabla para la aplicación"

    nombre = fields.Char("Nombre aula", required =True)
    piso = fields.Text("Descripción", required=True)
    capacidad = fields.Integer("Numero de sitios", required=True)
    disponibilidad = fields.Boolean("Si / no", default=True)