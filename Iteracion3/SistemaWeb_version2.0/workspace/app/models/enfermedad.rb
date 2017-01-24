class Enfermedad < ActiveRecord::Base
    validates_presence_of :codigo , :on => [ :create, :update ],:message => "no puede estar vacío"
    validates_presence_of :descripcion ,:on => [ :create, :update ], :message => "no puede estar vacío"
    validates_uniqueness_of :codigo, :on => [ :create, :update ],:message => "ya fue ingresada"

    has_and_belongs_to_many :pacientes
end
