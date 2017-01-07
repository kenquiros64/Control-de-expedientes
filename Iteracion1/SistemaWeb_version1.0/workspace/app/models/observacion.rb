class Observacion < ActiveRecord::Base
    has_and_belongs_to_many :citum
    has_and_belongs_to_many :pacientes
    belongs_to :medico
    validates_presence_of :descripcion , :on => [ :create, :update ],:message => "no puede estar vacío"
end
