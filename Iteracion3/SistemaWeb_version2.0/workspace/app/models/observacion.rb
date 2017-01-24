class Observacion < ActiveRecord::Base
    has_and_belongs_to_many :citum, dependent: :destroy, :join_table => :observacions_citas
    has_and_belongs_to_many :pacientes, dependent: :destroy, :join_table => :observacions_pacientes
    belongs_to :medico
    validates_presence_of :descripcion , :on => [ :create, :update ],:message => "no puede estar vacío"
end
